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

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.LabelConstants;
import org.eclipse.syson.diagram.general.view.directedit.GeneralViewDirectEditListener;
import org.eclipse.syson.diagram.general.view.directedit.grammars.generated.DirectEditLexer;
import org.eclipse.syson.diagram.general.view.directedit.grammars.generated.DirectEditListener;
import org.eclipse.syson.diagram.general.view.directedit.grammars.generated.DirectEditParser;
import org.eclipse.syson.sysml.Dependency;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.FeatureTyping;
import org.eclipse.syson.sysml.Usage;

/**
 * Label-related Java services used by the {@link GeneralViewDiagramDescriptionProvider}.
 *
 * @author arichard
 */
public class GeneralViewLabelService {

    /**
     * Return the container label for the given {@link Element}.
     *
     * @param element
     *            the given {@link Element}.
     * @return the container label for the given {@link Element}.
     */
    public String getContainerLabel(Element element) {
        return new ContainerLabelSwitch().doSwitch(element);
    }

    /**
     * Return the compartment item label for the given {@link Usage}.
     *
     * @param usage
     *            the given {@link Usage}.
     * @return the compartment item label for the given {@link Usage}.
     */
    public String getCompartmentItemLabel(Usage usage) {
        StringBuilder builder = new StringBuilder();
        builder.append(usage.getDeclaredName());
        builder.append(this.getTypingLabel(usage));
        builder.append(this.getValueLabel(usage));
        return builder.toString();
    }

    /**
     * Return the label of the value part of the given {@link Usage}.
     *
     * @param usage
     *            the given {@link Usage}.
     * @return the label of the value part of the given {@link Usage} if there is one, an empty string otherwise.
     */
    private String getValueLabel(Usage usage) {
        return "";
    }

    /**
     * Return the label of the typing part of the given {@link Usage}.
     *
     * @param usage
     *            the given {@link Usage}.
     * @return the label of the typing part of the given {@link Usage} if there is one, an empty string otherwise.
     */
    private String getTypingLabel(Usage usage) {
        StringBuilder builder = new StringBuilder();
        builder.append("");
        var featureTyping = usage.getOwnedRelationship().stream()
                .filter(FeatureTyping.class::isInstance)
                .map(FeatureTyping.class::cast)
                .findFirst();
        if (featureTyping.isPresent()) {
            var type = featureTyping.get().getType();
            if (type != null) {
                builder.append(" : ");
                builder.append(type.getDeclaredName());
            }
        }
        return builder.toString();
    }

    /**
     * Return the edge label for the given {@link Dependency}.
     *
     * @param dependency
     *            the given {@link Dependency}.
     * @return the edge label for the given {@link Dependency}.
     */
    public String getDependencyEdgeLabel(Dependency dependency) {
        return LabelConstants.OPEN_QUOTE + "dependency" + LabelConstants.CLOSE_QUOTE + LabelConstants.CR + dependency.getDeclaredName();
    }

    /**
     * Apply the direct edit result (i.e. the newLabel) to the given {@link Element}.
     *
     * @param element
     *            the given {@link Element}.
     * @param newLabel
     *            the new value to apply.
     * @return the given {@link Element}.
     */
    public Element directEdit(Element element, String newLabel) {
        DirectEditLexer lexer = new DirectEditLexer(CharStreams.fromString(newLabel));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DirectEditParser parser = new DirectEditParser(tokens);
        ParseTree tree = parser.expression();
        ParseTreeWalker walker = new ParseTreeWalker();
        DirectEditListener listener = new GeneralViewDirectEditListener(element);
        walker.walk(listener, tree);
        return element;
    }

    /**
     * Get the value to display when a direct edit has been called on the given {@link Element}.
     *
     * @param element
     *            the given {@link Element}.
     * @return the value to display.
     */
    public String getInitialDirectEditLabel(Element element) {
        if (element instanceof Usage usage) {
            return this.getCompartmentItemLabel(usage);
        }
        return element.getDeclaredName();
    }
}
