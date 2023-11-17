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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.components.view.RepresentationDescription;
import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.generated.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IDiagramElementDescriptionProvider;
import org.eclipse.sirius.components.view.builder.providers.IRepresentationDescriptionProvider;
import org.eclipse.sirius.components.view.diagram.DiagramPalette;
import org.eclipse.sirius.components.view.diagram.DiagramToolSection;
import org.eclipse.sirius.components.view.diagram.DropNodeTool;
import org.eclipse.sirius.components.view.diagram.DropTool;
import org.eclipse.sirius.components.view.diagram.NodeContainmentKind;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.syson.diagram.general.view.edges.DependencyEdgeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.edges.NestedPartEdgeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.edges.RedefinitionEdgeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.edges.SubclassificationEdgeDescriptionProvider;
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
                new DependencyEdgeDescriptionProvider(colorProvider),
                new SubclassificationEdgeDescriptionProvider(colorProvider),
                new RedefinitionEdgeDescriptionProvider(colorProvider)
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
                .dropNodeTool(this.createDropFromDiagramTool(cache))
                .dropTool(this.createDropFromExplorerTool(cache))
                .toolSections(this.createElementsToolSection(cache), this.addElementsToolSection(cache))
                .build();
    }

    private DropNodeTool createDropFromDiagramTool(IViewDiagramElementFinder cache) {
        var acceptedNodeTypes = new ArrayList<NodeDescription>();

        var optAttributeDefinitionNodeDescription = cache.getNodeDescription(AttributeDefinitionNodeDescriptionProvider.NAME);
        var optAttributeUsageNodeDescription = cache.getNodeDescription(AttributeUsageNodeDescriptionProvider.NAME);
        var optEnumerationDefinitionNodeDescription = cache.getNodeDescription(EnumerationDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceDefinitionNodeDescription = cache.getNodeDescription(InterfaceDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceUsageNodeDescription = cache.getNodeDescription(InterfaceUsageNodeDescriptionProvider.NAME);
        var optItemDefinitionNodeDescription = cache.getNodeDescription(ItemDefinitionNodeDescriptionProvider.NAME);
        var optItemUsageNodeDescription = cache.getNodeDescription(ItemUsageNodeDescriptionProvider.NAME);
        var optPackageNodeDescription = cache.getNodeDescription(PackageNodeDescriptionProvider.NAME);
        var optPartDefinitionNodeDescription = cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME);
        var optPartUsageNodeDescription = cache.getNodeDescription(PartUsageNodeDescriptionProvider.NAME);
        var optPortDefinitionNodeDescription = cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME);
        var optPortUsageNodeDescription = cache.getNodeDescription(PortUsageNodeDescriptionProvider.NAME);

        acceptedNodeTypes.add(optAttributeDefinitionNodeDescription.get());
        acceptedNodeTypes.add(optAttributeUsageNodeDescription.get());
        acceptedNodeTypes.add(optEnumerationDefinitionNodeDescription.get());
        acceptedNodeTypes.add(optInterfaceDefinitionNodeDescription.get());
        acceptedNodeTypes.add(optInterfaceUsageNodeDescription.get());
        acceptedNodeTypes.add(optItemDefinitionNodeDescription.get());
        acceptedNodeTypes.add(optItemUsageNodeDescription.get());
        acceptedNodeTypes.add(optPackageNodeDescription.get());
        acceptedNodeTypes.add(optPartDefinitionNodeDescription.get());
        acceptedNodeTypes.add(optPartUsageNodeDescription.get());
        acceptedNodeTypes.add(optPortDefinitionNodeDescription.get());
        acceptedNodeTypes.add(optPortUsageNodeDescription.get());

        var dropElementFromDiagram = this.viewBuilderHelper.newChangeContext()
                .expression("aql:droppedElement.dropElementFromDiagram(droppedNode, targetElement, targetNode, editingContext, diagramContext, convertedNodes)");

        return this.diagramBuilderHelper.newDropNodeTool()
                .name("Drop from Diagram")
                .acceptedNodeTypes(acceptedNodeTypes.toArray(new NodeDescription[acceptedNodeTypes.size()]))
                .body(dropElementFromDiagram.build())
                .build();
    }

    private DropTool createDropFromExplorerTool(IViewDiagramElementFinder cache) {
        var dropElementFromExplorer = this.viewBuilderHelper.newChangeContext()
                .expression("aql:self.dropElementFromExplorer(editingContext, diagramContext, selectedNode, convertedNodes)");

        return this.diagramBuilderHelper.newDropTool()
                .name("Drop from Explorer")
                .body(dropElementFromExplorer.build())
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
        var builder = this.diagramBuilderHelper.newNodeTool();

        var setValue = this.viewBuilderHelper.newSetValue()
                .featureName(SysmlPackage.eINSTANCE.getElement_DeclaredName().getName())
                .valueExpression(eClass.getName());

        var changeContextNewInstance = this.viewBuilderHelper.newChangeContext()
                .expression("aql:newInstance")
                .children(setValue.build());

        var createEClassInstance = this.viewBuilderHelper.newCreateInstance()
                .typeName(SysMLMetamodelHelper.buildQualifiedName(eClass))
                .referenceName(SysmlPackage.eINSTANCE.getRelationship_OwnedRelatedElement().getName())
                .variableName("newInstance")
                .children(changeContextNewInstance.build());

        var createView = this.diagramBuilderHelper.newCreateView()
                .containmentKind(NodeContainmentKind.CHILD_NODE)
                .elementDescription(nodeDescription)
                .parentViewExpression("aql:selectedNode")
                .semanticElementExpression("aql:newInstance")
                .variableName("newInstanceView");

        var changeContexMembership = this.viewBuilderHelper.newChangeContext()
                .expression("aql:newOwningMembership")
                .children(createEClassInstance.build(), createView.build());

        var createMembership = this.viewBuilderHelper.newCreateInstance()
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getOwningMembership()))
                .referenceName(SysmlPackage.eINSTANCE.getElement_OwnedRelationship().getName())
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
        var builder = this.diagramBuilderHelper.newNodeTool();

        var addExistingelements = this.viewBuilderHelper.newChangeContext()
                .expression("aql:self.addExistingElements(editingContext, diagramContext, selectedNode, convertedNodes)");

        return builder
                .name("Add existing elements")
                .body(addExistingelements.build())
                .build();
    }
}
