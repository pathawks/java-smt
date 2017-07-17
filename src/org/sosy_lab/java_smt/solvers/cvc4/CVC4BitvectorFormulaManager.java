/*
 *  JavaSMT is an API wrapper for a collection of SMT solvers.
 *  This file is part of JavaSMT.
 *
 *  Copyright (C) 2007-2015  Dirk Beyer
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.sosy_lab.java_smt.solvers.cvc4;

import edu.nyu.acsys.CVC4.BitVector;
import edu.nyu.acsys.CVC4.BitVectorExtract;
import edu.nyu.acsys.CVC4.Expr;
import edu.nyu.acsys.CVC4.ExprManager;
import edu.nyu.acsys.CVC4.Kind;
import edu.nyu.acsys.CVC4.Rational;
import edu.nyu.acsys.CVC4.Type;
import java.math.BigInteger;
import org.sosy_lab.java_smt.basicimpl.AbstractBitvectorFormulaManager;

public class CVC4BitvectorFormulaManager
    extends AbstractBitvectorFormulaManager<Expr, Type, ExprManager, Expr> {

  private final ExprManager exprManager;

  protected CVC4BitvectorFormulaManager(CVC4FormulaCreator pCreator) {
    super(pCreator);
    exprManager = pCreator.getExprManager();
  }

  @Override
  public Expr concat(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_CONCAT, pParam1, pParam2);
  }

  @Override
  public Expr extract(Expr pParam1, int pMsb, int pLsb, boolean signed) {
    Expr ext = exprManager.mkConst(new BitVectorExtract(pMsb, pLsb));
    return exprManager.mkExpr(Kind.BITVECTOR_EXTRACT, ext, pParam1);
  }

  @Override
  public Expr extend(Expr pParam1, int pExtensionBits, boolean signed) {
    Expr pParam2 = exprManager.mkConst(new Rational(pExtensionBits));
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SIGN_EXTEND, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_ZERO_EXTEND, pParam1, pParam2);
    }
  }

  @Override
  public Expr makeBitvectorImpl(int pLength, long pI) {
    return exprManager.mkConst(new BitVector(pI, pLength));
  }

  @Override
  protected Expr makeBitvectorImpl(int pLength, BigInteger pI) {
    return exprManager.mkConst(new BitVector(pI.toString(), pLength));
  }

  @Override
  public Expr makeVariableImpl(int length, String varName) {
    Type type = exprManager.mkBitVectorType(length);
    return getFormulaCreator().makeVariable(type, varName);
  }

  /**
   * Return a term representing the (arithmetic if signed is true) right shift of number by toShift.
   */
  @Override
  public Expr shiftRight(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_ASHR, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_LSHR, pParam1, pParam2);
    }
  }

  @Override
  public Expr shiftLeft(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_SHL, pParam1, pParam2);
  }

  @Override
  public Expr not(Expr pParam1) {
    return exprManager.mkExpr(Kind.BITVECTOR_NOT, pParam1);
  }

  @Override
  public Expr and(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_AND, pParam1, pParam2);
  }

  @Override
  public Expr or(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_OR, pParam1, pParam2);
  }

  @Override
  public Expr xor(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_XOR, pParam1, pParam2);
  }

  @Override
  public Expr negate(Expr pParam1) {
    return exprManager.mkExpr(Kind.BITVECTOR_NEG, pParam1);
  }

  @Override
  public Expr add(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_PLUS, pParam1, pParam2);
  }

  @Override
  public Expr subtract(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_SUB, pParam1, pParam2);
  }

  @Override
  public Expr divide(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SDIV, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_UDIV, pParam1, pParam2);
    }
  }

  @Override
  public Expr modulo(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SMOD, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_UREM, pParam1, pParam2);
    }
  }

  @Override
  public Expr multiply(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.BITVECTOR_MULT, pParam1, pParam2);
  }

  @Override
  public Expr equal(Expr pParam1, Expr pParam2) {
    return exprManager.mkExpr(Kind.EQUAL, pParam1, pParam2);
  }

  @Override
  public Expr lessThan(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SLT, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_ULT, pParam1, pParam2);
    }
  }

  @Override
  public Expr lessOrEquals(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SLE, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_ULE, pParam1, pParam2);
    }
  }

  @Override
  public Expr greaterThan(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SGT, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_UGT, pParam1, pParam2);
    }
  }

  @Override
  public Expr greaterOrEquals(Expr pParam1, Expr pParam2, boolean signed) {
    if (signed) {
      return exprManager.mkExpr(Kind.BITVECTOR_SGE, pParam1, pParam2);
    } else {
      return exprManager.mkExpr(Kind.BITVECTOR_UGE, pParam1, pParam2);
    }
  }
}
