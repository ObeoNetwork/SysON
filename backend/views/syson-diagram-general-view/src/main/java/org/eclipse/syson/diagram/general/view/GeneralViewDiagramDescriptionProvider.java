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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.components.view.RepresentationDescription;
import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.ChangeContextBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateInstanceBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateViewBuilder;
import org.eclipse.sirius.components.view.builder.generated.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.generated.NodeToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.SetValueBuilder;
import org.eclipse.sirius.components.view.builder.generated.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IDiagramElementDescriptionProvider;
import org.eclipse.sirius.components.view.builder.providers.IRepresentationDescriptionProvider;
import org.eclipse.sirius.components.view.diagram.DiagramPalette;
import org.eclipse.sirius.components.view.diagram.DiagramToolSection;
import org.eclipse.sirius.components.view.diagram.NodeContainmentKind;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.syson.diagram.general.view.edges.DependencyEdgeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.edges.NestedPartEdgeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.AttributeDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.AttributeUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.EnumerationDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.InterfaceDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.InterfaceUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.ItemDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.ItemUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PackageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PartDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PartUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PortDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PortUsageNodeDescriptionProvider;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Description of the General View diagram using the ViewBuilder API from Sirius Web.
 *
 * @author arichard
 */
public class GeneralViewDiagramDescriptionProvider implements IRepresentationDescriptionProvider {

    public static final String DEFAULT_LABEL_EXPRESSION = "aql:self.declaredName";

    public static final String DEFAULT_NODE_WIDTH = "125";

    public static final String DEFAULT_COMPARTMENT_NODE_HEIGHT = "60";

    public static final String DEFAULT_COMPARTMENT_NODE_ITEM_HEIGHT = "15";

    public static final String DEFAULT_CONTAINER_NODE_HEIGHT = "60";

    public static final String DEFAULT_BACKGROUND_COLOR = "white";

    public static final String DEFAULT_COMPARTMENT_BACKGROUND_COLOR = "transparent";

    public static final String DEFAULT_BORDER_COLOR = "black";

    public static final String DEFAULT_LABEL_COLOR = "black";

    public static final String DEFAULT_EDGE_COLOR = "black";

    private final DiagramBuilders diagramBuilderHelper = new DiagramBuilders();

    private final ViewBuilders viewBuilderHelper = new ViewBuilders();

    @Override
    public RepresentationDescription create(IColorProvider colorProvider) {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPackage());

        var diagramDescriptionBuilder = this.diagramBuilderHelper.newDiagramDescription();
        diagramDescriptionBuilder
                .autoLayout(false)
                .domainType(domainType)
                .name("General View")
                .titleExpression("General View__REACT_FLOW");

        var diagramDescription = diagramDescriptionBuilder.build();

        var cache = new ViewDiagramElementFinder();
        var diagramElementDescriptionProviders = List.of(
                new AttributeDefinitionNodeDescriptionProvider(colorProvider),
                new AttributeUsageNodeDescriptionProvider(colorProvider),
                new EnumerationDefinitionNodeDescriptionProvider(colorProvider),
                new InterfaceDefinitionNodeDescriptionProvider(colorProvider),
                new InterfaceUsageNodeDescriptionProvider(colorProvider),
                new ItemDefinitionNodeDescriptionProvider(colorProvider),
                new ItemUsageNodeDescriptionProvider(colorProvider),
                new PackageNodeDescriptionProvider(colorProvider),
                new PartDefinitionNodeDescriptionProvider(colorProvider),
                new PartUsageNodeDescriptionProvider(colorProvider),
                new PortDefinitionNodeDescriptionProvider(colorProvider),
                new PortUsageNodeDescriptionProvider(colorProvider),
                new NestedPartEdgeDescriptionProvider(colorProvider),
                new DependencyEdgeDescriptionProvider(colorProvider)
        );

        diagramElementDescriptionProviders.stream().
                map(IDiagramElementDescriptionProvider::create)
                .forEach(cache::put);
        diagramElementDescriptionProviders.forEach(diagramElementDescriptionProvider -> diagramElementDescriptionProvider.link(diagramDescription, cache));

        var palette = this.createDiagramPalette(cache);
        diagramDescription.setPalette(palette);

        return diagramDescription;
    }

    private DiagramPalette createDiagramPalette(IViewDiagramElementFinder cache) {
        return this.diagramBuilderHelper.newDiagramPalette()
                .toolSections(this.createElementsToolSection(cache), this.addElementsToolSection(cache))
                .build();
    }

    private DiagramToolSection createElementsToolSection(IViewDiagramElementFinder cache) {
        return this.diagramBuilderHelper.newDiagramToolSection()
                .name("Create")
                .nodeTools(this.createNodeToolFromPackage(cache.getNodeDescription(AttributeDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getAttributeDefinition()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(AttributeUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getAttributeUsage()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(EnumerationDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getEnumerationDefinition()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(InterfaceDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getInterfaceDefinition()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(InterfaceUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getInterfaceUsage()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(ItemDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getItemDefinition()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(ItemUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getItemUsage()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(PackageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPackage()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPartDefinition()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(PartUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPartUsage()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPortDefinition()),
                           this.createNodeToolFromPackage(cache.getNodeDescription(PortUsageNodeDescriptionProvider.NAME).get(), SysmlPackage.eINSTANCE.getPortUsage()))
                .build();
    }

    private NodeTool createNodeToolFromPackage(NodeDescription nodeDescription, EClass eClass) {
        NodeToolBuilder builder = this.diagramBuilderHelper.newNodeTool();

        SetValueBuilder setValue = this.viewBuilderHelper.newSetValue()
                .featureName("declaredName")
                .valueExpression(eClass.getName());

        ChangeContextBuilder changeContextNewInstance = this.viewBuilderHelper.newChangeContext()
                .expression("aql:newInstance")
                .children(setValue.build());

        CreateInstanceBuilder createEClassInstance = this.viewBuilderHelper.newCreateInstance()
                .typeName(SysMLMetamodelHelper.buildQualifiedName(eClass))
                .referenceName("ownedRelatedElement")
                .variableName("newInstance")
                .children(changeContextNewInstance.build());

        CreateViewBuilder createView = this.diagramBuilderHelper.newCreateView()
                .containmentKind(NodeContainmentKind.CHILD_NODE)
                .elementDescription(nodeDescription)
                .parentViewExpression("aql:selectedNode")
                .semanticElementExpression("aql:newInstance")
                .variableName("newInstanceView");

        ChangeContextBuilder changeContexMembership = this.viewBuilderHelper.newChangeContext()
                .expression("aql:newOwningMembership")
                .children(createEClassInstance.build(), createView.build());

        CreateInstanceBuilder createMembership = this.viewBuilderHelper.newCreateInstance()
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getOwningMembership()))
                .referenceName("ownedRelationship")
                .variableName("newOwningMembership")
                .children(changeContexMembership.build());

        return builder
                .name("New  " + eClass.getName())
                .body(createMembership.build())
                .build();
    }

    private DiagramToolSection addElementsToolSection(IViewDiagramElementFinder cache) {
        return this.diagramBuilderHelper.newDiagramToolSection()
                .name("Add")
                .nodeTools(this.addExistingElementsTool())
                .build();
    }

    private NodeTool addExistingElementsTool() {
        NodeToolBuilder builder = this.diagramBuilderHelper.newNodeTool();

        ChangeContextBuilder addExistingelements = this.viewBuilderHelper.newChangeContext()
                .expression("aql:self.addExistingElements(diagramContext, selectedNode, convertedNodes)");

        return builder
                .name("Add existing elements")
                .body(addExistingelements.build())
                .build();
    }
}
