/**
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.syson.sysml.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.syson.sysml.Conjugation;
import org.eclipse.syson.sysml.Differencing;
import org.eclipse.syson.sysml.Disjoining;
import org.eclipse.syson.sysml.Feature;
import org.eclipse.syson.sysml.FeatureDirectionKind;
import org.eclipse.syson.sysml.FeatureMembership;
import org.eclipse.syson.sysml.Intersecting;
import org.eclipse.syson.sysml.Membership;
import org.eclipse.syson.sysml.Multiplicity;
import org.eclipse.syson.sysml.Specialization;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.Type;
import org.eclipse.syson.sysml.Unioning;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#isIsConjugated <em>Is Conjugated</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#isIsSufficient <em>Is Sufficient</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getDifferencingType <em>Differencing Type</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getDirectedFeature <em>Directed Feature</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getEndFeature <em>End Feature</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getFeatureMembership <em>Feature Membership</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getInheritedFeature <em>Inherited Feature</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getInheritedMembership <em>Inherited Membership</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getIntersectingType <em>Intersecting Type</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedConjugator <em>Owned Conjugator</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedDifferencing <em>Owned Differencing</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedDisjoining <em>Owned Disjoining</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedEndFeature <em>Owned End Feature</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedFeature <em>Owned Feature</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedFeatureMembership <em>Owned Feature Membership</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedIntersecting <em>Owned Intersecting</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedSpecialization <em>Owned Specialization</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getOwnedUnioning <em>Owned Unioning</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.TypeImpl#getUnioningType <em>Unioning Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypeImpl extends NamespaceImpl implements Type {
    /**
     * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsAbstract()
     * @generated
     * @ordered
     */
    protected static final boolean IS_ABSTRACT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsAbstract()
     * @generated
     * @ordered
     */
    protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

    /**
     * The default value of the '{@link #isIsConjugated() <em>Is Conjugated</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsConjugated()
     * @generated
     * @ordered
     */
    protected static final boolean IS_CONJUGATED_EDEFAULT = false;

    /**
     * The default value of the '{@link #isIsSufficient() <em>Is Sufficient</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsSufficient()
     * @generated
     * @ordered
     */
    protected static final boolean IS_SUFFICIENT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsSufficient() <em>Is Sufficient</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsSufficient()
     * @generated
     * @ordered
     */
    protected boolean isSufficient = IS_SUFFICIENT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Type> getDifferencingType() {
        List<Type> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_DifferencingType(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getDirectedFeature() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_DirectedFeature(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getEndFeature() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_EndFeature(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getFeature() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_Feature(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<FeatureMembership> getFeatureMembership() {
        List<FeatureMembership> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_FeatureMembership(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getInheritedFeature() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_InheritedFeature(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Membership> getInheritedMembership() {
        List<Membership> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_InheritedMembership(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getInput() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_Input(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Type> getIntersectingType() {
        List<Type> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_IntersectingType(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsAbstract() {
        return this.isAbstract;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsAbstract(boolean newIsAbstract) {
        boolean oldIsAbstract = this.isAbstract;
        this.isAbstract = newIsAbstract;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.TYPE__IS_ABSTRACT, oldIsAbstract, this.isAbstract));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public boolean isIsConjugated() {
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsSufficient() {
        return this.isSufficient;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsSufficient(boolean newIsSufficient) {
        boolean oldIsSufficient = this.isSufficient;
        this.isSufficient = newIsSufficient;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.TYPE__IS_SUFFICIENT, oldIsSufficient, this.isSufficient));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Multiplicity getMultiplicity() {
        Multiplicity multiplicity = this.basicGetMultiplicity();
        return multiplicity != null && multiplicity.eIsProxy() ? (Multiplicity)this.eResolveProxy((InternalEObject)multiplicity) : multiplicity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Multiplicity basicGetMultiplicity() {
        // TODO: implement this method to return the 'Multiplicity' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getOutput() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_Output(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Conjugation getOwnedConjugator() {
        Conjugation ownedConjugator = this.basicGetOwnedConjugator();
        return ownedConjugator != null && ownedConjugator.eIsProxy() ? (Conjugation)this.eResolveProxy((InternalEObject)ownedConjugator) : ownedConjugator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Conjugation basicGetOwnedConjugator() {
        // TODO: implement this method to return the 'Owned Conjugator' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Differencing> getOwnedDifferencing() {
        List<Differencing> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedDifferencing(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Disjoining> getOwnedDisjoining() {
        List<Disjoining> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedDisjoining(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getOwnedEndFeature() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedEndFeature(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getOwnedFeature() {
        List<Feature> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedFeature(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<FeatureMembership> getOwnedFeatureMembership() {
        List<FeatureMembership> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedFeatureMembership(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Intersecting> getOwnedIntersecting() {
        List<Intersecting> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedIntersecting(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Specialization> getOwnedSpecialization() {
        List<Specialization> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedSpecialization(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Unioning> getOwnedUnioning() {
        List<Unioning> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_OwnedUnioning(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Type> getUnioningType() {
        List<Type> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getType_UnioningType(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Type> allSupertypes() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureDirectionKind directionOf(Feature feature) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Membership> inheritedMemberships(EList<Type> excluded) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public boolean specializes(Type supertype) {
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public boolean specializesFromLibrary(String libraryTypeName) {
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.TYPE__IS_ABSTRACT:
                return this.isIsAbstract();
            case SysmlPackage.TYPE__IS_CONJUGATED:
                return this.isIsConjugated();
            case SysmlPackage.TYPE__IS_SUFFICIENT:
                return this.isIsSufficient();
            case SysmlPackage.TYPE__DIFFERENCING_TYPE:
                return this.getDifferencingType();
            case SysmlPackage.TYPE__DIRECTED_FEATURE:
                return this.getDirectedFeature();
            case SysmlPackage.TYPE__END_FEATURE:
                return this.getEndFeature();
            case SysmlPackage.TYPE__FEATURE:
                return this.getFeature();
            case SysmlPackage.TYPE__FEATURE_MEMBERSHIP:
                return this.getFeatureMembership();
            case SysmlPackage.TYPE__INHERITED_FEATURE:
                return this.getInheritedFeature();
            case SysmlPackage.TYPE__INHERITED_MEMBERSHIP:
                return this.getInheritedMembership();
            case SysmlPackage.TYPE__INPUT:
                return this.getInput();
            case SysmlPackage.TYPE__INTERSECTING_TYPE:
                return this.getIntersectingType();
            case SysmlPackage.TYPE__MULTIPLICITY:
                if (resolve) return this.getMultiplicity();
                return this.basicGetMultiplicity();
            case SysmlPackage.TYPE__OUTPUT:
                return this.getOutput();
            case SysmlPackage.TYPE__OWNED_CONJUGATOR:
                if (resolve) return this.getOwnedConjugator();
                return this.basicGetOwnedConjugator();
            case SysmlPackage.TYPE__OWNED_DIFFERENCING:
                return this.getOwnedDifferencing();
            case SysmlPackage.TYPE__OWNED_DISJOINING:
                return this.getOwnedDisjoining();
            case SysmlPackage.TYPE__OWNED_END_FEATURE:
                return this.getOwnedEndFeature();
            case SysmlPackage.TYPE__OWNED_FEATURE:
                return this.getOwnedFeature();
            case SysmlPackage.TYPE__OWNED_FEATURE_MEMBERSHIP:
                return this.getOwnedFeatureMembership();
            case SysmlPackage.TYPE__OWNED_INTERSECTING:
                return this.getOwnedIntersecting();
            case SysmlPackage.TYPE__OWNED_SPECIALIZATION:
                return this.getOwnedSpecialization();
            case SysmlPackage.TYPE__OWNED_UNIONING:
                return this.getOwnedUnioning();
            case SysmlPackage.TYPE__UNIONING_TYPE:
                return this.getUnioningType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SysmlPackage.TYPE__IS_ABSTRACT:
                this.setIsAbstract((Boolean)newValue);
                return;
            case SysmlPackage.TYPE__IS_SUFFICIENT:
                this.setIsSufficient((Boolean)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case SysmlPackage.TYPE__IS_ABSTRACT:
                this.setIsAbstract(IS_ABSTRACT_EDEFAULT);
                return;
            case SysmlPackage.TYPE__IS_SUFFICIENT:
                this.setIsSufficient(IS_SUFFICIENT_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SysmlPackage.TYPE__IS_ABSTRACT:
                return this.isAbstract != IS_ABSTRACT_EDEFAULT;
            case SysmlPackage.TYPE__IS_CONJUGATED:
                return this.isIsConjugated() != IS_CONJUGATED_EDEFAULT;
            case SysmlPackage.TYPE__IS_SUFFICIENT:
                return this.isSufficient != IS_SUFFICIENT_EDEFAULT;
            case SysmlPackage.TYPE__DIFFERENCING_TYPE:
                return !this.getDifferencingType().isEmpty();
            case SysmlPackage.TYPE__DIRECTED_FEATURE:
                return !this.getDirectedFeature().isEmpty();
            case SysmlPackage.TYPE__END_FEATURE:
                return !this.getEndFeature().isEmpty();
            case SysmlPackage.TYPE__FEATURE:
                return !this.getFeature().isEmpty();
            case SysmlPackage.TYPE__FEATURE_MEMBERSHIP:
                return !this.getFeatureMembership().isEmpty();
            case SysmlPackage.TYPE__INHERITED_FEATURE:
                return !this.getInheritedFeature().isEmpty();
            case SysmlPackage.TYPE__INHERITED_MEMBERSHIP:
                return !this.getInheritedMembership().isEmpty();
            case SysmlPackage.TYPE__INPUT:
                return !this.getInput().isEmpty();
            case SysmlPackage.TYPE__INTERSECTING_TYPE:
                return !this.getIntersectingType().isEmpty();
            case SysmlPackage.TYPE__MULTIPLICITY:
                return this.basicGetMultiplicity() != null;
            case SysmlPackage.TYPE__OUTPUT:
                return !this.getOutput().isEmpty();
            case SysmlPackage.TYPE__OWNED_CONJUGATOR:
                return this.basicGetOwnedConjugator() != null;
            case SysmlPackage.TYPE__OWNED_DIFFERENCING:
                return !this.getOwnedDifferencing().isEmpty();
            case SysmlPackage.TYPE__OWNED_DISJOINING:
                return !this.getOwnedDisjoining().isEmpty();
            case SysmlPackage.TYPE__OWNED_END_FEATURE:
                return !this.getOwnedEndFeature().isEmpty();
            case SysmlPackage.TYPE__OWNED_FEATURE:
                return !this.getOwnedFeature().isEmpty();
            case SysmlPackage.TYPE__OWNED_FEATURE_MEMBERSHIP:
                return !this.getOwnedFeatureMembership().isEmpty();
            case SysmlPackage.TYPE__OWNED_INTERSECTING:
                return !this.getOwnedIntersecting().isEmpty();
            case SysmlPackage.TYPE__OWNED_SPECIALIZATION:
                return !this.getOwnedSpecialization().isEmpty();
            case SysmlPackage.TYPE__OWNED_UNIONING:
                return !this.getOwnedUnioning().isEmpty();
            case SysmlPackage.TYPE__UNIONING_TYPE:
                return !this.getUnioningType().isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
            case SysmlPackage.TYPE___ALL_SUPERTYPES:
                return this.allSupertypes();
            case SysmlPackage.TYPE___DIRECTION_OF__FEATURE:
                return this.directionOf((Feature)arguments.get(0));
            case SysmlPackage.TYPE___INHERITED_MEMBERSHIPS__ELIST:
                return this.inheritedMemberships((EList<Type>)arguments.get(0));
            case SysmlPackage.TYPE___SPECIALIZES__TYPE:
                return this.specializes((Type)arguments.get(0));
            case SysmlPackage.TYPE___SPECIALIZES_FROM_LIBRARY__STRING:
                return this.specializesFromLibrary((String)arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (isAbstract: ");
        result.append(this.isAbstract);
        result.append(", isSufficient: ");
        result.append(this.isSufficient);
        result.append(')');
        return result.toString();
    }

} //TypeImpl
