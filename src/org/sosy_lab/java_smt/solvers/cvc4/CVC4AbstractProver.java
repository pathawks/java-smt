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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import edu.nyu.acsys.CVC4.SmtEngine;
import org.sosy_lab.java_smt.api.BasicProverEnvironment;
import org.sosy_lab.java_smt.api.Model.ValueAssignment;
import org.sosy_lab.java_smt.api.SolverException;

abstract class CVC4AbstractProver<T> implements BasicProverEnvironment<T> {
  protected final CVC4FormulaCreator creator;
  protected final SmtEngine smtEngine;

  protected boolean closed = false;

  protected CVC4AbstractProver(CVC4FormulaCreator pFormulaCreator) {
    this.creator = pFormulaCreator;
    this.smtEngine = pFormulaCreator.getSmtEngine();
  }

  protected abstract CVC4Model getCVC4Model();

  @Override
  public CVC4Model getModel() {
    Preconditions.checkState(!closed);
    return CVC4Model.create(creator);
  }

  @Override
  public ImmutableList<ValueAssignment> getModelAssignments() throws SolverException {
    Preconditions.checkState(!closed);
    try (CVC4Model model = getModel()) {
      return model.modelToList();
    }
  }
}
