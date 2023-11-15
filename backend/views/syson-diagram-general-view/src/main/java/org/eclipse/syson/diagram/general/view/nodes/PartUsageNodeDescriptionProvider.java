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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.ChangeContextBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateInstanceBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateViewBuilder;
import org.eclipse.sirius.components.view.builder.generated.DeleteToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.ListLayoutStrategyDescriptionBuilder;
import org.eclipse.sirius.components.view.builder.generated.NodeToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.SetValueBuilder;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.NodeContainmentKind;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodePalette;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.SysMLMetamodelHelper;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the part usage node description.
 *
 * @author arichard
 */
public class PartUsageNodeDescriptionProvider extends AbstractNodeDescriptionProvider {

    public static final String NAME = "GV Node PartUsage";


    public PartUsageNodeDescriptionProvider(IColorProvider colorProvider) {
        super(colorProvider);
    }

    @Override
    public NodeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage());
        return this.diagramBuilderHelper.newNodeDescription()
                .childrenDescriptions(this.createUsageAttributesCompartment(NAME), this.createUsagePortsCompartment(NAME))
                .childrenLayoutStrategy(new ListLayoutStrategyDescriptionBuilder().build())
                .defaultHeightExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_CONTAINER_NODE_HEIGHT)
                .defaultWidthExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_NODE_WIDTH)
                .domainType(domainType)
                .labelExpression("aql:self.getContainerLabel()")
                .name(NAME)
                .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
                .style(this.createUsageNodeStyle())
                .userResizable(true)
                .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
                .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        var dependencyTargetNodeDescriptions = new ArrayList<NodeDescription>();

        var optAttributeDefinitionNodeDescription = cache.getNodeDescription(AttributeDefinitionNodeDescriptionProvider.NAME);
        var optAttributeUsageNodeDescription = cache.getNodeDescription(AttributeUsageNodeDescriptionProvider.NAME);
        var optEnumerationDefinitionNodeDescription = cache.getNodeDescription(EnumerationDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceDefinitionNodeDescription = cache.getNodeDescription(InterfaceDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceUsageNodeDescription = cache.getNodeDescription(InterfaceUsageNodeDescriptionProvider.NAME);
        var optItemDefinitionNodeDescription = cache.getNodeDescription(ItemDefinitionNodeDescriptionProvider.NAME);
        var optItemUsageNodeDescription = cache.getNodeDescription(ItemUsageNodeDescriptionProvider.NAME);
        var optPackageNodeDescription = cache.getNodeDescription(PackageNodeDescriptionProvider.NAME);
        var optPartDefinitionNodeDescription = cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME);
        var optPortDefinitionNodeDescription = cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME);
        var optPortUsageNodeDescription = cache.getNodeDescription(PortUsageNodeDescriptionProvider.NAME);

        if (optAttributeDefinitionNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optAttributeDefinitionNodeDescription.get());
        }
        if (optAttributeUsageNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optAttributeUsageNodeDescription.get());
        }
        if (optEnumerationDefinitionNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optEnumerationDefinitionNodeDescription.get());
        }
        if (optInterfaceDefinitionNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optInterfaceDefinitionNodeDescription.get());
        }
        if (optInterfaceUsageNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optInterfaceUsageNodeDescription.get());
        }
        if (optItemDefinitionNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optItemDefinitionNodeDescription.get());
        }
        if (optItemUsageNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optItemUsageNodeDescription.get());
        }
        if (optPackageNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optPackageNodeDescription.get());
        }
        if (optPartDefinitionNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optPartDefinitionNodeDescription.get());
        }
        if (optPortDefinitionNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optPortDefinitionNodeDescription.get());
        }
        if (optPortUsageNodeDescription.isPresent()) {
            dependencyTargetNodeDescriptions.add(optPortUsageNodeDescription.get());
        }

        var optPartUsageNodeDescription = cache.getNodeDescription(PartUsageNodeDescriptionProvider.NAME);
        if (optPartUsageNodeDescription.isPresent()) {
            NodeDescription nodeDescription = optPartUsageNodeDescription.get();
            diagramDescription.getNodeDescriptions().add(nodeDescription);
            dependencyTargetNodeDescriptions.add(nodeDescription);
            nodeDescription.setPalette(this.createPartUsagePalette(nodeDescription, dependencyTargetNodeDescriptions));
        }
    }

    private NodePalette createPartUsagePalette(NodeDescription nodeDescription, List<NodeDescription> dependencyTargetNodeDescriptions) {
        NodeToolBuilder nodeTool = this.createNestedPartNodeTool(nodeDescription);

        ChangeContextBuilder changeContext = this.viewBuilderHelper.newChangeContext();
        changeContext
                .expression("aql:self.deleteFromModel()");

        DeleteToolBuilder deleteTool = this.diagramBuilderHelper.newDeleteTool()
                .name("Delete from Model")
                .body(changeContext.build());

        return this.diagramBuilderHelper.newNodePalette()
                .deleteTool(deleteTool.build())
                .nodeTools(nodeTool.build())
                .edgeTools(this.createDependencyEdgeTool(dependencyTargetNodeDescriptions))
                .build();
    }

    private NodeToolBuilder createNestedPartNodeTool(NodeDescription nodeDescription) {
        SetValueBuilder setValue = this.viewBuilderHelper.newSetValue();
        setValue
                .featureName("declaredName")
                .valueExpression("PartUsage");

        ChangeContextBuilder changeContextNewInstance = this.viewBuilderHelper.newChangeContext();
        changeContextNewInstance
                .expression("aql:newInstance")
                .children(setValue.build());

        CreateInstanceBuilder createEClassInstance =  this.viewBuilderHelper.newCreateInstance();
        createEClassInstance
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage()))
                .referenceName("ownedRelatedElement")
                .variableName("newInstance")
                .children(changeContextNewInstance.build());

        CreateViewBuilder createView = this.diagramBuilderHelper.newCreateView();
        createView
                .containmentKind(NodeContainmentKind.CHILD_NODE)
                .elementDescription(nodeDescription)
                .parentViewExpression("aql:diagram")
                .semanticElementExpression("aql:newInstance")
                .variableName("newInstanceView");

        ChangeContextBuilder changeContexMembership = this.viewBuilderHelper.newChangeContext();
        changeContexMembership
                .expression("aql:newFeatureMembership")
                .children(createEClassInstance.build(), createView.build());

        CreateInstanceBuilder createMembership =  this.viewBuilderHelper.newCreateInstance();
        createMembership
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getFeatureMembership()))
                .referenceName("ownedRelationship")
                .variableName("newFeatureMembership")
                .children(changeContexMembership.build());

        NodeToolBuilder nodeTool = this.diagramBuilderHelper.newNodeTool();
        nodeTool
                .name("PartUsage")
                .body(createMembership.build())
                .build();
        return nodeTool;
    }
}
