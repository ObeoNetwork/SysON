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
package org.eclipse.syson.diagram.general.view.nodes;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.ChangeContextBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateInstanceBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateViewBuilder;
import org.eclipse.sirius.components.view.builder.generated.FreeFormLayoutStrategyDescriptionBuilder;
import org.eclipse.sirius.components.view.builder.generated.NodeToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.SetValueBuilder;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.NodeContainmentKind;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodePalette;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.sirius.components.view.diagram.NodeToolSection;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.SysMLMetamodelHelper;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the part definition node description.
 *
 * @author arichard
 */
public class PackageNodeDescriptionProvider extends AbstractNodeDescriptionProvider {

    public static final String NAME = "GV Node PackageDefinition";

    public PackageNodeDescriptionProvider(IColorProvider colorProvider) {
        super(colorProvider);
    }

    @Override
    public NodeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPackage());
        return this.diagramBuilderHelper.newNodeDescription()
                .childrenLayoutStrategy(new FreeFormLayoutStrategyDescriptionBuilder().build())
                .defaultHeightExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_CONTAINER_NODE_HEIGHT)
                .defaultWidthExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_NODE_WIDTH)
                .domainType(domainType)
                .labelExpression("aql:self.getContainerLabel()")
                .name(NAME)
                .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
                .style(this.createDefinitionNodeStyle())
                .userResizable(true)
                .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
                .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        NodeDescription packageNodeDescription = cache.getNodeDescription(NAME).get();
        diagramDescription.getNodeDescriptions().add(packageNodeDescription);
        packageNodeDescription.getReusedChildNodeDescriptions().add(cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME).get());
        packageNodeDescription.getReusedChildNodeDescriptions().add(cache.getNodeDescription(PartUsageNodeDescriptionProvider.NAME).get());
        packageNodeDescription.getReusedChildNodeDescriptions().add(cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME).get());
        packageNodeDescription.getReusedChildNodeDescriptions().add(cache.getNodeDescription(PortUsageNodeDescriptionProvider.NAME).get());
        packageNodeDescription.setPalette(this.createNodePalette(cache));
    }

    private NodePalette createNodePalette(IViewDiagramElementFinder cache) {
        return this.diagramBuilderHelper.newNodePalette()
                .toolSections(this.createNodeToolSection(cache))
                .build();
    }

    private NodeToolSection createNodeToolSection(IViewDiagramElementFinder cache) {
        return this.diagramBuilderHelper.newNodeToolSection()
                .name("Create")
                .nodeTools(this.createNodeTool(cache.getNodeDescription(AttributeDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getAttributeDefinition()),
                           this.createNodeTool(cache.getNodeDescription(AttributeUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getAttributeUsage()),
                           this.createNodeTool(cache.getNodeDescription(EnumerationDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getEnumerationDefinition()),
                           this.createNodeTool(cache.getNodeDescription(InterfaceDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getInterfaceDefinition()),
                           this.createNodeTool(cache.getNodeDescription(InterfaceUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getInterfaceUsage()),
                           this.createNodeTool(cache.getNodeDescription(ItemDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getItemDefinition()),
                           this.createNodeTool(cache.getNodeDescription(ItemUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getItemUsage()),
                           this.createNodeTool(cache.getNodeDescription(PackageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPackage()),
                           this.createNodeTool(cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPartDefinition()),
                           this.createNodeTool(cache.getNodeDescription(PartUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPartUsage()),
                           this.createNodeTool(cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPortDefinition()),
                           this.createNodeTool(cache.getNodeDescription(PortUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPortUsage()))
                .build();
    }

    private NodeTool createNodeTool(NodeDescription nodeDescription, EClass eClass) {
        NodeToolBuilder builder = this.diagramBuilderHelper.newNodeTool();

        SetValueBuilder setValue = this.viewBuilderHelper.newSetValue();
        setValue
            .featureName("declaredName")
                .valueExpression(eClass.getName());

        ChangeContextBuilder changeContextNewInstance = this.viewBuilderHelper.newChangeContext();
        changeContextNewInstance
            .expression("aql:newInstance")
            .children(setValue.build());

        CreateInstanceBuilder createEClassInstance =  this.viewBuilderHelper.newCreateInstance();
        createEClassInstance
            .typeName(SysMLMetamodelHelper.buildQualifiedName(eClass))
            .referenceName("ownedRelatedElement")
            .variableName("newInstance")
            .children(changeContextNewInstance.build());

        CreateViewBuilder createView = this.diagramBuilderHelper.newCreateView();
        createView
            .containmentKind(NodeContainmentKind.CHILD_NODE)
            .elementDescription(nodeDescription)
            .parentViewExpression("aql:selectedNode")
            .semanticElementExpression("aql:newInstance")
            .variableName("newInstanceView");

        ChangeContextBuilder changeContexMembership = this.viewBuilderHelper.newChangeContext();
        changeContexMembership
            .expression("aql:newOwningMembership")
            .children(createEClassInstance.build(), createView.build());

        CreateInstanceBuilder createMembership =  this.viewBuilderHelper.newCreateInstance();
        createMembership
            .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getOwningMembership()))
            .referenceName("ownedRelationship")
            .variableName("newOwningMembership")
            .children(changeContexMembership.build());

        return builder
                .name(eClass.getName())
                .body(createMembership.build())
                .build();
    }
}
