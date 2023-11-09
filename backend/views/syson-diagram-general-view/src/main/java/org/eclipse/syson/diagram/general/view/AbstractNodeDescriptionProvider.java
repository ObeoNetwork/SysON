/*******************************************************************************
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
 *******************************************************************************/
package org.eclipse.syson.diagram.general.view;

import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.components.view.builder.generated.ChangeContextBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateInstanceBuilder;
import org.eclipse.sirius.components.view.builder.generated.DeleteToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.generated.NodeDescriptionBuilder;
import org.eclipse.sirius.components.view.builder.generated.NodeToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.SetValueBuilder;
import org.eclipse.sirius.components.view.builder.generated.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.INodeDescriptionProvider;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodePalette;
import org.eclipse.sirius.components.view.diagram.NodeStyleDescription;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Common pieces of node descriptions shared by {@link INodeDescriptionProvider} in General View.
 *
 * @author arichard
 */
public abstract class AbstractNodeDescriptionProvider implements INodeDescriptionProvider {

    private static final String AQL_SELF = "aql:self";

    protected final ViewBuilders viewBuilderHelper = new ViewBuilders();

    protected final DiagramBuilders diagramBuilderHelper = new DiagramBuilders();

    protected final IColorProvider colorProvider;

    public AbstractNodeDescriptionProvider(IColorProvider colorProvider) {
        this.colorProvider = Objects.requireNonNull(colorProvider);
    }

    protected NodeDescription createDefinitionAttributesCompartment(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .childrenDescriptions(this.createDefinitionAttributesCompartmentItem(name))
                .childrenLayoutStrategy(this.diagramBuilderHelper.newListLayoutStrategyDescription().build())
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getElement()))
                .labelExpression("attributes")
                .name(name + " AttributesCompartment")
                .semanticCandidatesExpression(AQL_SELF)
                .style(this.createDefinitionCompartmentNodeStyle())
                .userResizable(false)
                .palette(this.createAttributesCompartmentPalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createDefinitionAttributesCompartmentItem(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getAttributeUsage()))
                .labelExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_EXPRESSION)
                .name(name + " AttributesCompartmentItem")
                .semanticCandidatesExpression("aql:self.ownedAttribute")
                .style(this.createDefinitionCompartmentItemNodeStyle())
                .userResizable(false)
                .palette(this.createDefaultNodePalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createDefinitionPortsCompartment(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .childrenDescriptions(this.createDefinitionPortsCompartmentItem(name))
                .childrenLayoutStrategy(this.diagramBuilderHelper.newListLayoutStrategyDescription().build())
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getElement()))
                .labelExpression("ports")
                .name(name + " PortsCompartment")
                .semanticCandidatesExpression(AQL_SELF)
                .style(this.createDefinitionCompartmentNodeStyle())
                .userResizable(false)
                .palette(this.createPortsCompartmentPalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createDefinitionPortsCompartmentItem(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPortUsage()))
                .labelExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_EXPRESSION)
                .name(name + "PortsCompartmentItem")
                .semanticCandidatesExpression("aql:self.ownedPort")
                .style(this.createDefinitionCompartmentItemNodeStyle())
                .userResizable(false)
                .palette(this.createDefaultNodePalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createDefinitionItemsCompartment(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .childrenDescriptions(this.createDefinitionItemsCompartmentItem(name))
                .childrenLayoutStrategy(this.diagramBuilderHelper.newListLayoutStrategyDescription().build())
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getElement()))
                .labelExpression("items")
                .name(name + " ItemsCompartment")
                .semanticCandidatesExpression(AQL_SELF)
                .style(this.createDefinitionCompartmentNodeStyle())
                .userResizable(false)
                .palette(this.createItemsCompartmentPalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createDefinitionItemsCompartmentItem(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getItemUsage()))
                .labelExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_EXPRESSION)
                .name(name + " ItemsCompartmentItem")
                .semanticCandidatesExpression("aql:self.ownedItem")
                .style(this.createDefinitionCompartmentItemNodeStyle())
                .userResizable(false)
                .palette(this.createDefaultNodePalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeStyleDescription createDefinitionNodeStyle() {
        return this.diagramBuilderHelper.newRectangularNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(0)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BACKGROUND_COLOR))
                .displayHeaderSeparator(true)
                .heightComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_CONTAINER_NODE_HEIGHT)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(true)
                .widthComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_WIDTH)
                .withHeader(true)
                .build();
    }

    protected NodeStyleDescription createDefinitionCompartmentNodeStyle() {
        return this.diagramBuilderHelper.newRectangularNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(0)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_COMPARTMENT_BACKGROUND_COLOR))
                .displayHeaderSeparator(false)
                .fontSize(12).heightComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_COMPARTMENT_NODE_HEIGHT)
                .italic(true)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(false)
                .widthComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_WIDTH)
                .withHeader(true)
                .build();
    }

    protected NodeStyleDescription createDefinitionCompartmentItemNodeStyle() {
        return this.diagramBuilderHelper.newIconLabelNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(0)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BACKGROUND_COLOR))
                .heightComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_COMPARTMENT_NODE_HEIGHT)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(true)
                .widthComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_WIDTH)
                .build();
    }

    protected NodeDescription createUsageAttributesCompartment(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .childrenDescriptions(this.createUsageAttributesCompartmentItem(name))
                .childrenLayoutStrategy(this.diagramBuilderHelper.newListLayoutStrategyDescription().build())
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getElement()))
                .labelExpression("attributes")
                .name(name + " AttributesCompartment")
                .semanticCandidatesExpression(AQL_SELF)
                .style(this.createUsageCompartmentNodeStyle())
                .userResizable(false)
                .palette(this.createAttributesCompartmentPalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createUsageAttributesCompartmentItem(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getAttributeUsage()))
                .labelExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_EXPRESSION)
                .name(name + " AttributesCompartmentItem")
                .semanticCandidatesExpression("aql:self.nestedAttribute")
                .style(this.createUsageCompartmentItemNodeStyle())
                .userResizable(false)
                .palette(this.createDefaultNodePalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createUsagePortsCompartment(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .childrenDescriptions(this.createUsagePortsCompartmentItem(name))
                .childrenLayoutStrategy(this.diagramBuilderHelper.newListLayoutStrategyDescription().build())
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getElement()))
                .labelExpression("ports")
                .name(name + " PortsCompartment")
                .semanticCandidatesExpression(AQL_SELF)
                .style(this.createUsageCompartmentNodeStyle())
                .userResizable(false)
                .palette(this.createPortsCompartmentPalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeDescription createUsagePortsCompartmentItem(String name) {
        NodeDescriptionBuilder attributeNodeBuilder = this.diagramBuilderHelper.newNodeDescription();
        return attributeNodeBuilder
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPortUsage()))
                .labelExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_EXPRESSION)
                .name(name + "PortsCompartmentItem")
                .semanticCandidatesExpression("aql:self.nestedPort")
                .style(this.createUsageCompartmentItemNodeStyle())
                .userResizable(false)
                .palette(this.createDefaultNodePalette())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .build();
    }

    protected NodeStyleDescription createUsageNodeStyle() {
        return this.diagramBuilderHelper.newRectangularNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(10)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BACKGROUND_COLOR))
                .displayHeaderSeparator(true)
                .heightComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_CONTAINER_NODE_HEIGHT)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(true)
                .widthComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_WIDTH)
                .withHeader(true)
                .build();
    }

    protected NodeStyleDescription createUsageCompartmentNodeStyle() {
        return this.diagramBuilderHelper.newRectangularNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(0)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_COMPARTMENT_BACKGROUND_COLOR))
                .displayHeaderSeparator(false)
                .fontSize(12)
                .heightComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_COMPARTMENT_NODE_HEIGHT)
                .italic(true)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(false)
                .widthComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_WIDTH)
                .withHeader(true)
                .build();
    }

    protected NodeStyleDescription createUsageCompartmentItemNodeStyle() {
        return this.diagramBuilderHelper.newIconLabelNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(0)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BACKGROUND_COLOR))
                .heightComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_COMPARTMENT_NODE_HEIGHT)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(true)
                .widthComputationExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_WIDTH)
                .build();
    }

    protected NodePalette createDefaultNodePalette() {
        ChangeContextBuilder changeContext = this.viewBuilderHelper.newChangeContext();
        changeContext.expression("aql:self.deleteWithMembership()");

        DeleteToolBuilder deleteTool = this.diagramBuilderHelper.newDeleteTool()
                .name("Delete from Model")
                .body(changeContext.build());

        return this.diagramBuilderHelper.newNodePalette()
                .deleteTool(deleteTool.build())
                .build();
    }

    protected NodePalette createAttributesCompartmentPalette() {
        return this.diagramBuilderHelper.newNodePalette()
                .nodeTools(this.createCompartmentNodeTool(SysmlPackage.eINSTANCE.getAttributeUsage(), "attribute"))
                .build();
    }

    protected NodePalette createPortsCompartmentPalette() {
        return this.diagramBuilderHelper.newNodePalette()
                .nodeTools(this.createCompartmentNodeTool(SysmlPackage.eINSTANCE.getPortUsage(), "port"))
                .build();
    }

    protected NodePalette createItemsCompartmentPalette() {
        return this.diagramBuilderHelper.newNodePalette()
                .nodeTools(this.createCompartmentNodeTool(SysmlPackage.eINSTANCE.getItemUsage(), "item"))
                .build();
    }

    protected NodeTool createCompartmentNodeTool(EClass eClass, String defaultLabel) {
        NodeToolBuilder builder = this.diagramBuilderHelper.newNodeTool();

        SetValueBuilder setValue = this.viewBuilderHelper.newSetValue();
        setValue
                .featureName("declaredName")
                .valueExpression(defaultLabel);

        ChangeContextBuilder changeContextNewInstance = this.viewBuilderHelper.newChangeContext();
        changeContextNewInstance
                .expression("aql:newInstance")
                .children(setValue.build());

        CreateInstanceBuilder createInstance =  this.viewBuilderHelper.newCreateInstance();
        createInstance
                .typeName(SysMLMetamodelHelper.buildQualifiedName(eClass))
                .referenceName("ownedRelatedElement")
                .variableName("newInstance")
                .children(changeContextNewInstance.build());

        ChangeContextBuilder changeContextMembership = this.viewBuilderHelper.newChangeContext();
        changeContextMembership
                .expression("aql:newFeatureMembership")
                .children(createInstance.build());

        CreateInstanceBuilder createMembership =  this.viewBuilderHelper.newCreateInstance();
        createMembership
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getFeatureMembership()))
                .referenceName("ownedRelationship")
                .variableName("newFeatureMembership")
                .children(changeContextMembership.build());

        return builder
                .name(eClass.getName())
                .body(createMembership.build())
                .build();
    }
}
