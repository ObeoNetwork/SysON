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
package org.eclipse.syson.diagram.general.view.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.components.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.components.diagrams.Diagram;
import org.eclipse.sirius.components.diagrams.Node;
import org.eclipse.sirius.components.diagrams.ViewCreationRequest;
import org.eclipse.sirius.components.diagrams.components.NodeContainmentKind;
import org.eclipse.sirius.components.diagrams.description.NodeDescription;
import org.eclipse.sirius.components.emf.services.IDAdapter;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.SysMLMetamodelHelper;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.Package;

/**
 * Tool-related Java services used by the {@link GeneralViewDiagramDescriptionProvider}.
 *
 * @author arichard
 */
public class GeneralViewToolService {

    /**
     * Call by "Add existing elements" Tools from General View diagram or General View node Package. Add nodes that are
     * not present in the diagram or the selectedNode (i.e. a Package).
     *
     * @param pkg
     *            the {@link Package} corresponding to the target object of the {@link Diagram} or the {@link Node}
     *            Package on which the tool has been called.
     * @param diagramContext
     *            the {@link IDiagramContext} of the tool. It corresponds to a variable accessible from the variable
     *            manager.
     * @param selectedNode
     *            the selected node on which the tool has been called (may be null if the tool has been called from the
     *            diagram). It corresponds to a variable accessible from the variable manager.
     * @param convertedNodes
     *            the map of all existing node descriptions in the {@link DiagramDescription} of this {@link Diagram}.
     *            It corresponds to a variable accessible from the variable manager.
     * @return the given {@link Package}.
     */
    public Package addExistingElements(Package pkg, IDiagramContext diagramContext, Node selectedNode,
            Map<org.eclipse.sirius.components.view.diagram.NodeDescription, NodeDescription> convertedNodes) {
        var members = pkg.getOwnedMember();
        members.stream()
                .filter(member -> !this.isPresent(member, this.getChildNodes(diagramContext, selectedNode)))
                .forEach(member -> this.createView(member, diagramContext, this.getParentElementId(diagramContext, selectedNode), convertedNodes));
        return pkg;
    }

    private List<Node> getChildNodes(IDiagramContext diagramContext, Node selectedNode) {
        var childNodes = new ArrayList<Node>();
        if (selectedNode != null) {
            childNodes.addAll(selectedNode.getChildNodes());
        } else {
            Diagram diagram = diagramContext.getDiagram();
            childNodes.addAll(diagram.getNodes());
        }
        return childNodes;
    }

    private boolean isPresent(Element element, List<Node> nodes) {
        return nodes.stream().anyMatch(node -> node.getTargetObjectId().equals(this.getIdFromIDAdapter(element)));
    }

    private void createView(Element element, IDiagramContext diagramContext, String parentElementId, Map<org.eclipse.sirius.components.view.diagram.NodeDescription, NodeDescription> convertedNodes) {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(element.eClass());

        var descriptionId = convertedNodes.keySet().stream()
                .filter(viewNodeDesc -> viewNodeDesc.getDomainType().equals(domainType))
                .map(viewNodeDesc -> convertedNodes.get(viewNodeDesc).getId())
                .findFirst();

        if (descriptionId.isPresent()) {
            var request = ViewCreationRequest.newViewCreationRequest()
                    .containmentKind(NodeContainmentKind.CHILD_NODE)
                    .descriptionId(descriptionId.get())
                    .parentElementId(parentElementId)
                    .targetObjectId(this.getIdFromIDAdapter(element))
                    .build();
            diagramContext.getViewCreationRequests().add(request);
        }
    }

    private String getIdFromIDAdapter(EObject eObject) {
        return eObject.eAdapters().stream()
                .filter(IDAdapter.class::isInstance)
                .map(IDAdapter.class::cast)
                .findFirst()
                .map(IDAdapter::getId)
                .map(Object::toString)
                .orElse(null);
    }

    private String getParentElementId(IDiagramContext diagramContext, Node selectedNode) {
        if (selectedNode != null) {
            return selectedNode.getId();
        }
        return diagramContext.getDiagram().getId();
    }
}
