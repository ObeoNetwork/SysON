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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.syson.sysml.ActionUsage;
import org.eclipse.syson.sysml.AllocationUsage;
import org.eclipse.syson.sysml.AnalysisCaseUsage;
import org.eclipse.syson.sysml.AttributeUsage;
import org.eclipse.syson.sysml.CalculationUsage;
import org.eclipse.syson.sysml.CaseUsage;
import org.eclipse.syson.sysml.Classifier;
import org.eclipse.syson.sysml.ConcernUsage;
import org.eclipse.syson.sysml.ConnectorAsUsage;
import org.eclipse.syson.sysml.ConstraintUsage;
import org.eclipse.syson.sysml.Definition;
import org.eclipse.syson.sysml.EnumerationUsage;
import org.eclipse.syson.sysml.FeatureMembership;
import org.eclipse.syson.sysml.FlowConnectionUsage;
import org.eclipse.syson.sysml.InterfaceUsage;
import org.eclipse.syson.sysml.ItemUsage;
import org.eclipse.syson.sysml.MetadataUsage;
import org.eclipse.syson.sysml.OccurrenceUsage;
import org.eclipse.syson.sysml.PartUsage;
import org.eclipse.syson.sysml.PortUsage;
import org.eclipse.syson.sysml.ReferenceUsage;
import org.eclipse.syson.sysml.RenderingUsage;
import org.eclipse.syson.sysml.RequirementUsage;
import org.eclipse.syson.sysml.StateUsage;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.TransitionUsage;
import org.eclipse.syson.sysml.Usage;
import org.eclipse.syson.sysml.UseCaseUsage;
import org.eclipse.syson.sysml.VariantMembership;
import org.eclipse.syson.sysml.VerificationCaseUsage;
import org.eclipse.syson.sysml.ViewUsage;
import org.eclipse.syson.sysml.ViewpointUsage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#isIsReference <em>Is Reference</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#isIsVariation <em>Is Variation</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getDirectedUsage <em>Directed Usage</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedAction <em>Nested Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedAllocation <em>Nested Allocation</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedAnalysisCase <em>Nested Analysis Case</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedAttribute <em>Nested Attribute</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedCalculation <em>Nested Calculation</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedCase <em>Nested Case</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedConcern <em>Nested Concern</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedConnection <em>Nested Connection</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedConstraint <em>Nested Constraint</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedEnumeration <em>Nested Enumeration</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedFlow <em>Nested Flow</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedInterface <em>Nested Interface</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedItem <em>Nested Item</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedMetadata <em>Nested Metadata</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedOccurrence <em>Nested Occurrence</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedPart <em>Nested Part</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedPort <em>Nested Port</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedReference <em>Nested Reference</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedRendering <em>Nested Rendering</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedRequirement <em>Nested Requirement</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedState <em>Nested State</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedTransition <em>Nested Transition</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedUsage <em>Nested Usage</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedUseCase <em>Nested Use Case</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedVerificationCase <em>Nested Verification Case</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedView <em>Nested View</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getNestedViewpoint <em>Nested Viewpoint</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getOwningDefinition <em>Owning Definition</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getOwningUsage <em>Owning Usage</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getUsage <em>Usage</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getVariant <em>Variant</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UsageImpl#getVariantMembership <em>Variant Membership</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UsageImpl extends FeatureImpl implements Usage {
    /**
     * The default value of the '{@link #isIsReference() <em>Is Reference</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsReference()
     * @generated
     * @ordered
     */
    protected static final boolean IS_REFERENCE_EDEFAULT = false;

    /**
     * The default value of the '{@link #isIsVariation() <em>Is Variation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsVariation()
     * @generated
     * @ordered
     */
    protected static final boolean IS_VARIATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsVariation() <em>Is Variation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsVariation()
     * @generated
     * @ordered
     */
    protected boolean isVariation = IS_VARIATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UsageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getUsage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Classifier> getDefinition() {
        List<Classifier> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_Definition(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Usage> getDirectedUsage() {
        List<Usage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_DirectedUsage(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public boolean isIsReference() {
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsVariation() {
        return this.isVariation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsVariation(boolean newIsVariation) {
        boolean oldIsVariation = this.isVariation;
        this.isVariation = newIsVariation;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.USAGE__IS_VARIATION, oldIsVariation, this.isVariation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ActionUsage> getNestedAction() {
        List<ActionUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedAction(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<AllocationUsage> getNestedAllocation() {
        List<AllocationUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedAllocation(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<AnalysisCaseUsage> getNestedAnalysisCase() {
        List<AttributeUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedAnalysisCase(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<AttributeUsage> getNestedAttribute() {
        List<AttributeUsage> attributes = new ArrayList<>();
        this.getOwnedRelationship().stream()
            .filter(FeatureMembership.class::isInstance)
            .map(FeatureMembership.class::cast)
            .flatMap(fm -> fm.getOwnedRelatedElement().stream())
            .filter(AttributeUsage.class::isInstance)
            .map(AttributeUsage.class::cast)
            .forEach(attributes::add);
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedAttribute(), attributes.size(), attributes.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<CalculationUsage> getNestedCalculation() {
        List<CalculationUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedCalculation(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<CaseUsage> getNestedCase() {
        List<CaseUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedCase(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ConcernUsage> getNestedConcern() {
        List<ConcernUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedConcern(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ConnectorAsUsage> getNestedConnection() {
        List<ConnectorAsUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedConnection(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ConstraintUsage> getNestedConstraint() {
        List<ConstraintUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedConstraint(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<EnumerationUsage> getNestedEnumeration() {
        List<EnumerationUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedEnumeration(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<FlowConnectionUsage> getNestedFlow() {
        List<FlowConnectionUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedFlow(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<InterfaceUsage> getNestedInterface() {
        List<InterfaceUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedInterface(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ItemUsage> getNestedItem() {
        List<ItemUsage> nestedItems = new ArrayList<>();
        this.getOwnedRelationship().stream()
            .filter(FeatureMembership.class::isInstance)
            .map(FeatureMembership.class::cast)
            .flatMap(fm -> fm.getOwnedRelatedElement().stream())
            .filter(ItemUsage.class::isInstance)
            .map(ItemUsage.class::cast)
            .forEach(nestedItems::add);
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedItem(), nestedItems.size(), nestedItems.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<MetadataUsage> getNestedMetadata() {
        List<MetadataUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedMetadata(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<OccurrenceUsage> getNestedOccurrence() {
        List<OccurrenceUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedOccurrence(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<PartUsage> getNestedPart() {
        List<PartUsage> nestedParts = new ArrayList<>();
        this.getOwnedRelationship().stream()
            .filter(FeatureMembership.class::isInstance)
            .map(FeatureMembership.class::cast)
            .flatMap(fm -> fm.getOwnedRelatedElement().stream())
            .filter(PartUsage.class::isInstance)
            .map(PartUsage.class::cast)
            .forEach(nestedParts::add);
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedPart(), nestedParts.size(), nestedParts.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<PortUsage> getNestedPort() {
        List<PortUsage> nestedPorts = new ArrayList<>();
        this.getOwnedRelationship().stream()
                .filter(FeatureMembership.class::isInstance)
                .map(FeatureMembership.class::cast)
                .flatMap(fm -> fm.getOwnedRelatedElement().stream())
                .filter(PortUsage.class::isInstance)
                .map(PortUsage.class::cast)
                .forEach(nestedPorts::add);
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedPort(), nestedPorts.size(), nestedPorts.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ReferenceUsage> getNestedReference() {
        List<ReferenceUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedReference(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<RenderingUsage> getNestedRendering() {
        List<RenderingUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedRendering(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<RequirementUsage> getNestedRequirement() {
        List<RequirementUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedRequirement(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<StateUsage> getNestedState() {
        List<StateUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedState(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<TransitionUsage> getNestedTransition() {
        List<TransitionUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedTransition(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Usage> getNestedUsage() {
        List<Usage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedUsage(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<UseCaseUsage> getNestedUseCase() {
        List<UseCaseUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedUseCase(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<VerificationCaseUsage> getNestedVerificationCase() {
        List<VerificationCaseUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedVerificationCase(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ViewUsage> getNestedView() {
        List<ViewUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedView(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ViewpointUsage> getNestedViewpoint() {
        List<ViewpointUsage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_NestedViewpoint(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Definition getOwningDefinition() {
        Definition owningDefinition = this.basicGetOwningDefinition();
        return owningDefinition != null && owningDefinition.eIsProxy() ? (Definition)this.eResolveProxy((InternalEObject)owningDefinition) : owningDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Definition basicGetOwningDefinition() {
        // TODO: implement this method to return the 'Owning Definition' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Usage getOwningUsage() {
        Usage owningUsage = this.basicGetOwningUsage();
        return owningUsage != null && owningUsage.eIsProxy() ? (Usage)this.eResolveProxy((InternalEObject)owningUsage) : owningUsage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Usage basicGetOwningUsage() {
        // TODO: implement this method to return the 'Owning Usage' reference
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
    public EList<Usage> getUsage() {
        List<Usage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_Usage(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Usage> getVariant() {
        List<Usage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_Variant(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<VariantMembership> getVariantMembership() {
        List<VariantMembership> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getUsage_VariantMembership(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.USAGE__IS_REFERENCE:
                return this.isIsReference();
            case SysmlPackage.USAGE__IS_VARIATION:
                return this.isIsVariation();
            case SysmlPackage.USAGE__DEFINITION:
                return this.getDefinition();
            case SysmlPackage.USAGE__DIRECTED_USAGE:
                return this.getDirectedUsage();
            case SysmlPackage.USAGE__NESTED_ACTION:
                return this.getNestedAction();
            case SysmlPackage.USAGE__NESTED_ALLOCATION:
                return this.getNestedAllocation();
            case SysmlPackage.USAGE__NESTED_ANALYSIS_CASE:
                return this.getNestedAnalysisCase();
            case SysmlPackage.USAGE__NESTED_ATTRIBUTE:
                return this.getNestedAttribute();
            case SysmlPackage.USAGE__NESTED_CALCULATION:
                return this.getNestedCalculation();
            case SysmlPackage.USAGE__NESTED_CASE:
                return this.getNestedCase();
            case SysmlPackage.USAGE__NESTED_CONCERN:
                return this.getNestedConcern();
            case SysmlPackage.USAGE__NESTED_CONNECTION:
                return this.getNestedConnection();
            case SysmlPackage.USAGE__NESTED_CONSTRAINT:
                return this.getNestedConstraint();
            case SysmlPackage.USAGE__NESTED_ENUMERATION:
                return this.getNestedEnumeration();
            case SysmlPackage.USAGE__NESTED_FLOW:
                return this.getNestedFlow();
            case SysmlPackage.USAGE__NESTED_INTERFACE:
                return this.getNestedInterface();
            case SysmlPackage.USAGE__NESTED_ITEM:
                return this.getNestedItem();
            case SysmlPackage.USAGE__NESTED_METADATA:
                return this.getNestedMetadata();
            case SysmlPackage.USAGE__NESTED_OCCURRENCE:
                return this.getNestedOccurrence();
            case SysmlPackage.USAGE__NESTED_PART:
                return this.getNestedPart();
            case SysmlPackage.USAGE__NESTED_PORT:
                return this.getNestedPort();
            case SysmlPackage.USAGE__NESTED_REFERENCE:
                return this.getNestedReference();
            case SysmlPackage.USAGE__NESTED_RENDERING:
                return this.getNestedRendering();
            case SysmlPackage.USAGE__NESTED_REQUIREMENT:
                return this.getNestedRequirement();
            case SysmlPackage.USAGE__NESTED_STATE:
                return this.getNestedState();
            case SysmlPackage.USAGE__NESTED_TRANSITION:
                return this.getNestedTransition();
            case SysmlPackage.USAGE__NESTED_USAGE:
                return this.getNestedUsage();
            case SysmlPackage.USAGE__NESTED_USE_CASE:
                return this.getNestedUseCase();
            case SysmlPackage.USAGE__NESTED_VERIFICATION_CASE:
                return this.getNestedVerificationCase();
            case SysmlPackage.USAGE__NESTED_VIEW:
                return this.getNestedView();
            case SysmlPackage.USAGE__NESTED_VIEWPOINT:
                return this.getNestedViewpoint();
            case SysmlPackage.USAGE__OWNING_DEFINITION:
                if (resolve) return this.getOwningDefinition();
                return this.basicGetOwningDefinition();
            case SysmlPackage.USAGE__OWNING_USAGE:
                if (resolve) return this.getOwningUsage();
                return this.basicGetOwningUsage();
            case SysmlPackage.USAGE__USAGE:
                return this.getUsage();
            case SysmlPackage.USAGE__VARIANT:
                return this.getVariant();
            case SysmlPackage.USAGE__VARIANT_MEMBERSHIP:
                return this.getVariantMembership();
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
            case SysmlPackage.USAGE__IS_VARIATION:
                this.setIsVariation((Boolean)newValue);
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
            case SysmlPackage.USAGE__IS_VARIATION:
                this.setIsVariation(IS_VARIATION_EDEFAULT);
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
            case SysmlPackage.USAGE__IS_REFERENCE:
                return this.isIsReference() != IS_REFERENCE_EDEFAULT;
            case SysmlPackage.USAGE__IS_VARIATION:
                return this.isVariation != IS_VARIATION_EDEFAULT;
            case SysmlPackage.USAGE__DEFINITION:
                return !this.getDefinition().isEmpty();
            case SysmlPackage.USAGE__DIRECTED_USAGE:
                return !this.getDirectedUsage().isEmpty();
            case SysmlPackage.USAGE__NESTED_ACTION:
                return !this.getNestedAction().isEmpty();
            case SysmlPackage.USAGE__NESTED_ALLOCATION:
                return !this.getNestedAllocation().isEmpty();
            case SysmlPackage.USAGE__NESTED_ANALYSIS_CASE:
                return !this.getNestedAnalysisCase().isEmpty();
            case SysmlPackage.USAGE__NESTED_ATTRIBUTE:
                return !this.getNestedAttribute().isEmpty();
            case SysmlPackage.USAGE__NESTED_CALCULATION:
                return !this.getNestedCalculation().isEmpty();
            case SysmlPackage.USAGE__NESTED_CASE:
                return !this.getNestedCase().isEmpty();
            case SysmlPackage.USAGE__NESTED_CONCERN:
                return !this.getNestedConcern().isEmpty();
            case SysmlPackage.USAGE__NESTED_CONNECTION:
                return !this.getNestedConnection().isEmpty();
            case SysmlPackage.USAGE__NESTED_CONSTRAINT:
                return !this.getNestedConstraint().isEmpty();
            case SysmlPackage.USAGE__NESTED_ENUMERATION:
                return !this.getNestedEnumeration().isEmpty();
            case SysmlPackage.USAGE__NESTED_FLOW:
                return !this.getNestedFlow().isEmpty();
            case SysmlPackage.USAGE__NESTED_INTERFACE:
                return !this.getNestedInterface().isEmpty();
            case SysmlPackage.USAGE__NESTED_ITEM:
                return !this.getNestedItem().isEmpty();
            case SysmlPackage.USAGE__NESTED_METADATA:
                return !this.getNestedMetadata().isEmpty();
            case SysmlPackage.USAGE__NESTED_OCCURRENCE:
                return !this.getNestedOccurrence().isEmpty();
            case SysmlPackage.USAGE__NESTED_PART:
                return !this.getNestedPart().isEmpty();
            case SysmlPackage.USAGE__NESTED_PORT:
                return !this.getNestedPort().isEmpty();
            case SysmlPackage.USAGE__NESTED_REFERENCE:
                return !this.getNestedReference().isEmpty();
            case SysmlPackage.USAGE__NESTED_RENDERING:
                return !this.getNestedRendering().isEmpty();
            case SysmlPackage.USAGE__NESTED_REQUIREMENT:
                return !this.getNestedRequirement().isEmpty();
            case SysmlPackage.USAGE__NESTED_STATE:
                return !this.getNestedState().isEmpty();
            case SysmlPackage.USAGE__NESTED_TRANSITION:
                return !this.getNestedTransition().isEmpty();
            case SysmlPackage.USAGE__NESTED_USAGE:
                return !this.getNestedUsage().isEmpty();
            case SysmlPackage.USAGE__NESTED_USE_CASE:
                return !this.getNestedUseCase().isEmpty();
            case SysmlPackage.USAGE__NESTED_VERIFICATION_CASE:
                return !this.getNestedVerificationCase().isEmpty();
            case SysmlPackage.USAGE__NESTED_VIEW:
                return !this.getNestedView().isEmpty();
            case SysmlPackage.USAGE__NESTED_VIEWPOINT:
                return !this.getNestedViewpoint().isEmpty();
            case SysmlPackage.USAGE__OWNING_DEFINITION:
                return this.basicGetOwningDefinition() != null;
            case SysmlPackage.USAGE__OWNING_USAGE:
                return this.basicGetOwningUsage() != null;
            case SysmlPackage.USAGE__USAGE:
                return !this.getUsage().isEmpty();
            case SysmlPackage.USAGE__VARIANT:
                return !this.getVariant().isEmpty();
            case SysmlPackage.USAGE__VARIANT_MEMBERSHIP:
                return !this.getVariantMembership().isEmpty();
        }
        return super.eIsSet(featureID);
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
        result.append(" (isVariation: ");
        result.append(this.isVariation);
        result.append(')');
        return result.toString();
    }

} //UsageImpl
