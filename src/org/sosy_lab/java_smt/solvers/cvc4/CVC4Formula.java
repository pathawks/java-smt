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

import edu.nyu.acsys.CVC4.Expr;

import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.NumeralFormula.IntegerFormula;

public class CVC4Formula implements Formula {

  private final Expr cvc4term;

  CVC4Formula(Expr term) {
    cvc4term = term;
  }

  @Override
  public final String toString() {
    return cvc4term.toString();
  }

  @Override
  public final boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof CVC4Formula)) {
      return false;
    }
    return cvc4term.equals(((CVC4Formula) o).cvc4term);
  }

  @Override
  public final int hashCode() {
    // unfortunately we cannot do better, as hashCode is not implemented in the
    // CVC4Wrapper
    return 0;
  }

  final Expr getTerm() {
    return cvc4term;
  }

  static final class CVC4IntegerFormula extends CVC4Formula implements IntegerFormula {
    CVC4IntegerFormula(Expr pTerm) {
      super(pTerm);
    }
  }

  static final class CVC4BooleanFormula extends CVC4Formula implements BooleanFormula {
    CVC4BooleanFormula(Expr pTerm) {
      super(pTerm);
    }
  }
}
