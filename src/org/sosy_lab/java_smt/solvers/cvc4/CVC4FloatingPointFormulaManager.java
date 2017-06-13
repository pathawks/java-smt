/*
 *  JavaSMT is an API wrapper for a collection of SMT solvers.
 *  This file is part of JavaSMT.
 *
 *  Copyright (C) 2007-2016  Dirk Beyer
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

import edu.nyu.acsys.CVC4.Expr;
import edu.nyu.acsys.CVC4.ExprManager;
import edu.nyu.acsys.CVC4.Type;
import java.math.BigDecimal;
import org.sosy_lab.java_smt.api.FloatingPointRoundingMode;
import org.sosy_lab.java_smt.api.FormulaType;
import org.sosy_lab.java_smt.api.FormulaType.FloatingPointType;
import org.sosy_lab.java_smt.basicimpl.AbstractFloatingPointFormulaManager;

public class CVC4FloatingPointFormulaManager
    extends AbstractFloatingPointFormulaManager<Expr, Type, ExprManager, Expr> {
  protected final ExprManager exprManager;

  protected CVC4FloatingPointFormulaManager(CVC4FormulaCreator pCreator) {
    super(pCreator);
    exprManager = pCreator.getExprManager();
  }

  @Override
  protected Expr getDefaultRoundingMode() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr getRoundingModeImpl(FloatingPointRoundingMode pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makeNumberImpl(
      double pN, FloatingPointType pType, Expr pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makeNumberImpl(
      BigDecimal pN, FloatingPointType pType, Expr pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makeNumberImpl(
      String pN, FloatingPointType pType, Expr pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makeVariableImpl(String pVar, FloatingPointType pType) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makePlusInfinityImpl(FloatingPointType pType) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makeMinusInfinityImpl(FloatingPointType pType) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr makeNaNImpl(FloatingPointType pType) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr castToImpl(Expr pNumber, FormulaType<?> pTargetType, Expr pRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr castFromImpl(
      Expr pNumber, boolean pSigned, FloatingPointType pTargetType, Expr pRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr negate(Expr pParam1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr add(Expr pParam1, Expr pParam2, Expr pRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr subtract(Expr pParam1, Expr pParam2, Expr pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr divide(Expr pParam1, Expr pParam2, Expr pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr multiply(Expr pParam1, Expr pParam2, Expr pFloatingPointRoundingMode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr assignment(Expr pParam1, Expr pParam2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr equalWithFPSemantics(Expr pParam1, Expr pParam2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr greaterThan(Expr pParam1, Expr pParam2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr greaterOrEquals(Expr pParam1, Expr pParam2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr lessThan(Expr pParam1, Expr pParam2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr lessOrEquals(Expr pParam1, Expr pParam2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr isNaN(Expr pParam) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr isInfinity(Expr pParam) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr isZero(Expr pParam) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Expr isSubnormal(Expr pParam) {
    // TODO Auto-generated method stub
    return null;
  }
}
