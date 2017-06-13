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
package org.sosy_lab.java_smt.basicimpl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.sosy_lab.common.rationals.Rational;
import org.sosy_lab.java_smt.api.ArrayFormula;
import org.sosy_lab.java_smt.api.BitvectorFormula;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.Model;
import org.sosy_lab.java_smt.api.NumeralFormula.IntegerFormula;
import org.sosy_lab.java_smt.api.NumeralFormula.RationalFormula;

public abstract class AbstractModel<TFormulaInfo, TType, TEnv> implements Model {
  public Map<String, Object> evaluation;
  protected final FormulaCreator<TFormulaInfo, TType, TEnv, ?> creator;

  protected AbstractModel(FormulaCreator<TFormulaInfo, TType, TEnv, ?> creator) {
    this.creator = creator;
    evaluation = new HashMap<String, Object>();
  }

  @Nullable
  @Override
  public BigInteger evaluate(IntegerFormula f) {
    return (BigInteger) evaluateImpl(creator.extractInfo(f));
  }

  @Nullable
  @Override
  public Rational evaluate(RationalFormula f) {
    return (Rational) evaluateImpl(creator.extractInfo(f));
  }

  @Nullable
  @Override
  public Boolean evaluate(BooleanFormula f) {
    return (Boolean) evaluateImpl(creator.extractInfo(f));
  }

  @Nullable
  @Override
  public BigInteger evaluate(BitvectorFormula f) {
    return (BigInteger) evaluateImpl(creator.extractInfo(f));
  }

  @Nullable
  @Override
  public final Object evaluate(Formula f) {
    Preconditions.checkArgument(
        !(f instanceof ArrayFormula),
        "cannot compute a simple constant evaluation for an array-formula");
    return evaluateImpl(creator.extractInfo(f));
  }

  protected abstract Object evaluateImpl(TFormulaInfo f);

  @Override
  public String toString() {
    return Joiner.on('\n').join(iterator());
  }

  public abstract static class CachingAbstractModel<TFormulaInfo, TType, TEnv>
      extends AbstractModel<TFormulaInfo, TType, TEnv> {

    private @Nullable ImmutableList<ValueAssignment> modelAssignments = null;

    protected CachingAbstractModel(FormulaCreator<TFormulaInfo, TType, TEnv, ?> pCreator) {
      super(pCreator);
    }

    @Override
    public final Iterator<ValueAssignment> iterator() {
      if (modelAssignments == null) {
        modelAssignments = modelToList();
      }
      return modelAssignments.iterator();
    }

    protected abstract ImmutableList<ValueAssignment> modelToList();
  }
}
