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
package org.eclipse.syson.application.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistry;
import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistryConfigurer;
import org.eclipse.sirius.components.core.api.IFeedbackMessageService;
import org.eclipse.sirius.components.emf.services.EditingContext;
import org.eclipse.sirius.components.emf.services.IDAdapter;
import org.eclipse.sirius.components.interpreter.AQLInterpreter;
import org.eclipse.sirius.components.representations.IRepresentationDescription;
import org.eclipse.sirius.components.view.ChangeContext;
import org.eclipse.sirius.components.view.View;
import org.eclipse.sirius.components.view.ViewFactory;
import org.eclipse.sirius.components.view.emf.form.ViewFormDescriptionConverter;
import org.eclipse.sirius.components.view.form.CheckboxDescription;
import org.eclipse.sirius.components.view.form.FormDescription;
import org.eclipse.sirius.components.view.form.FormElementFor;
import org.eclipse.sirius.components.view.form.FormElementIf;
import org.eclipse.sirius.components.view.form.FormFactory;
import org.eclipse.sirius.components.view.form.GroupDescription;
import org.eclipse.sirius.components.view.form.GroupDisplayMode;
import org.eclipse.sirius.components.view.form.LabelDescription;
import org.eclipse.sirius.components.view.form.PageDescription;
import org.eclipse.sirius.components.view.form.RadioDescription;
import org.eclipse.sirius.components.view.form.TextfieldDescription;
import org.eclipse.sirius.components.view.form.WidgetDescription;
import org.eclipse.sirius.components.widgets.reference.ReferenceFactory;
import org.eclipse.sirius.components.widgets.reference.ReferenceWidgetDescription;
import org.eclipse.sirius.web.services.api.representations.IInMemoryViewRegistry;
import org.eclipse.syson.application.services.DetailsViewService;
import org.eclipse.syson.sysml.SysmlPackage;
import org.springframework.context.annotation.Configuration;

/**
 * Provides custom Details view for SysML elements.
 *
 * @author arichard
 */
@Configuration
public class SysMLv2PropertiesConfigurer implements IPropertiesDescriptionRegistryConfigurer {

    private static final String CORE_PROPERTIES = "Core Properties";

    private static final String ADVANCED_PROPERTIES = "Advanced Properties";

    private static final String REDEFINITION_PROPERTIES = "Redefinition Properties";

    private static final String SUBCLASSIFICATION_PROPERTIES = "Subclassification Properties";

    private final ComposedAdapterFactory composedAdapterFactory;

    private final ViewFormDescriptionConverter converter;

    private final IFeedbackMessageService feedbackMessageService;

    private final IInMemoryViewRegistry viewRegistry;

    public SysMLv2PropertiesConfigurer(ComposedAdapterFactory composedAdapterFactory, ViewFormDescriptionConverter converter, IFeedbackMessageService feedbackMessageService,
            IInMemoryViewRegistry viewRegistry) {
        this.composedAdapterFactory = Objects.requireNonNull(composedAdapterFactory);
        this.viewRegistry = Objects.requireNonNull(viewRegistry);
        this.converter = Objects.requireNonNull(converter);
        this.feedbackMessageService = Objects.requireNonNull(feedbackMessageService);
    }

    @Override
    public void addPropertiesDescriptions(IPropertiesDescriptionRegistry registry) {
        // Build the actual FormDescription that will be used in Detail view.
        FormDescription viewFormDescription = this.createDetailsView();

        // The FormDescription must be part of View inside a proper EMF Resource to be correctly handled
        URI uri = URI.createURI(EditingContext.RESOURCE_SCHEME + ":///" + UUID.nameUUIDFromBytes(SysMLv2PropertiesConfigurer.class.getCanonicalName().getBytes()));
        Resource resource = new XMIResourceImpl(uri);
        View view = org.eclipse.sirius.components.view.ViewFactory.eINSTANCE.createView();
        resource.getContents().add(view);
        view.getDescriptions().add(viewFormDescription);

        view.eAllContents().forEachRemaining(eObject -> {
            eObject.eAdapters().add(new IDAdapter(UUID.nameUUIDFromBytes(EcoreUtil.getURI(eObject).toString().getBytes())));
        });

        this.viewRegistry.register(view);

        // Convert the View-based FormDescription and register the result into the system
        AQLInterpreter interpreter = new AQLInterpreter(List.of(), List.of(new DetailsViewService(this.composedAdapterFactory, this.feedbackMessageService)), List.of(SysmlPackage.eINSTANCE));
        IRepresentationDescription converted = this.converter.convert(viewFormDescription, List.of(), interpreter);
        if (converted instanceof org.eclipse.sirius.components.forms.description.FormDescription formDescription) {
            formDescription.getPageDescriptions().forEach(registry::add);
        }
    }

    private FormDescription createDetailsView() {
        FormDescription form = FormFactory.eINSTANCE.createFormDescription();
        form.setName("SysON Details View");
        form.setDomainType("sysml::Element");
        form.setTitleExpression("sysml::Element");

        PageDescription pageCore = FormFactory.eINSTANCE.createPageDescription();
        pageCore.setDomainType("sysml::Element");
        pageCore.setPreconditionExpression("");
        pageCore.setLabelExpression("Core");
        pageCore.getGroups().add(this.createCorePropertiesGroup());
        pageCore.getGroups().add(this.createRedefinitionPropertiesGroup());
        pageCore.getGroups().add(this.createSubclassificationPropertiesGroup());

        PageDescription pageAdvanced = FormFactory.eINSTANCE.createPageDescription();
        pageAdvanced.setDomainType("sysml::Element");
        pageAdvanced.setPreconditionExpression("");
        pageAdvanced.setLabelExpression("Advanced");
        pageAdvanced.getGroups().add(this.createAdvancedPropertiesGroup());

        form.getPages().add(pageAdvanced);
        form.getPages().add(pageCore);

        return form;
    }

    private GroupDescription createCorePropertiesGroup() {
        GroupDescription group = FormFactory.eINSTANCE.createGroupDescription();
        group.setDisplayMode(GroupDisplayMode.LIST);
        group.setName(CORE_PROPERTIES);
        group.setLabelExpression(CORE_PROPERTIES);
        group.setSemanticCandidatesExpression("aql:self");

        group.getChildren().add(this.createCoreWidgets());

        return group;
    }

    private GroupDescription createAdvancedPropertiesGroup() {
        GroupDescription group = FormFactory.eINSTANCE.createGroupDescription();
        group.setDisplayMode(GroupDisplayMode.LIST);
        group.setName(ADVANCED_PROPERTIES);
        group.setLabelExpression(ADVANCED_PROPERTIES);
        group.setSemanticCandidatesExpression("aql:self");

        group.getChildren().add(this.createAdvancedWidgets());

        return group;
    }

    private GroupDescription createRedefinitionPropertiesGroup() {
        GroupDescription group = FormFactory.eINSTANCE.createGroupDescription();
        group.setDisplayMode(GroupDisplayMode.LIST);
        group.setName(REDEFINITION_PROPERTIES);
        group.setLabelExpression(REDEFINITION_PROPERTIES);
        group.setSemanticCandidatesExpression("aql:self.ownedRelationship->filter(sysml::Redefinition)");

        group.getChildren().add(this.createCoreWidgets());

        return group;
    }

    private GroupDescription createSubclassificationPropertiesGroup() {
        GroupDescription group = FormFactory.eINSTANCE.createGroupDescription();
        group.setDisplayMode(GroupDisplayMode.LIST);
        group.setName(SUBCLASSIFICATION_PROPERTIES);
        group.setLabelExpression(SUBCLASSIFICATION_PROPERTIES);
        group.setSemanticCandidatesExpression("aql:self.ownedRelationship->filter(sysml::Subclassification)");

        group.getChildren().add(this.createCoreWidgets());

        return group;
    }

    private FormElementFor createCoreWidgets() {
        FormElementFor forElt = FormFactory.eINSTANCE.createFormElementFor();
        forElt.setName("Widgets for Core Group");
        forElt.setIterator("eStructuralFeature");
        forElt.setIterableExpression("aql:self.getCoreFeatures()");
        forElt.getChildren().addAll(this.createWidgets());
        return forElt;
    }

    private FormElementFor createAdvancedWidgets() {
        FormElementFor forElt = FormFactory.eINSTANCE.createFormElementFor();
        forElt.setName("Widgets for Advanced Group");
        forElt.setIterator("eStructuralFeature");
        forElt.setIterableExpression("aql:self.getAdvancedFeatures()");
        forElt.getChildren().addAll(this.createWidgets());
        return forElt;
    }

    private List<FormElementIf> createWidgets() {
        List<FormElementIf> widgets = new ArrayList<>();

        FormElementIf label = FormFactory.eINSTANCE.createFormElementIf();
        label.setName("Read-only String Attributes");
        label.setPredicateExpression("aql:eStructuralFeature.isReadOnlyStringAttribute()");
        label.getChildren().add(this.createLabelWidget());
        widgets.add(label);

        FormElementIf textfield = FormFactory.eINSTANCE.createFormElementIf();
        textfield.setName("String Attributes");
        textfield.setPredicateExpression("aql:eStructuralFeature.isStringAttribute()");
        textfield.getChildren().add(this.createTextfieldWidget());
        widgets.add(textfield);

        FormElementIf checkbox = FormFactory.eINSTANCE.createFormElementIf();
        checkbox.setName("Boolean Attributes");
        checkbox.setPredicateExpression("aql:eStructuralFeature.isBooleanAttribute()");
        checkbox.getChildren().add(this.createCheckboxWidget());
        widgets.add(checkbox);

        FormElementIf radio = FormFactory.eINSTANCE.createFormElementIf();
        radio.setName("Radio Attributes");
        radio.setPredicateExpression("aql:eStructuralFeature.isEnumAttribute()");
        radio.getChildren().add(this.createRadioWidget());
        widgets.add(radio);

        FormElementIf refWidget = FormFactory.eINSTANCE.createFormElementIf();
        refWidget.setName("ReferenceWidget References");
        refWidget.setPredicateExpression("aql:eStructuralFeature.isReference()");
        refWidget.getChildren().add(this.createReferenceWidget());
        widgets.add(refWidget);

        FormElementIf number = FormFactory.eINSTANCE.createFormElementIf();
        number.setName("Number Attributes");
        number.setPredicateExpression("aql:eStructuralFeature.isNumberAttribute()");
        number.getChildren().add(this.createTextfieldWidget());
        widgets.add(number);

        return widgets;
    }

    private WidgetDescription createLabelWidget() {
        LabelDescription label = FormFactory.eINSTANCE.createLabelDescription();
        label.setName("LabelWidget");
        label.setLabelExpression("aql:self.getDetailsViewLabel(eStructuralFeature)");
        label.setValueExpression("aql:self.eGet(eStructuralFeature)");
        return label;
    }

    private WidgetDescription createTextfieldWidget() {
        TextfieldDescription textfield = FormFactory.eINSTANCE.createTextfieldDescription();
        textfield.setName("TextfieldWidget");
        textfield.setLabelExpression("aql:self.getDetailsViewLabel(eStructuralFeature)");
        textfield.setValueExpression("aql:self.eGet(eStructuralFeature)");
        textfield.setIsEnabledExpression("aql:not(eStructuralFeature.isReadOnly())");
        ChangeContext setNewValueOperation = ViewFactory.eINSTANCE.createChangeContext();
        setNewValueOperation.setExpression("aql:self.setNewValue(eStructuralFeature, " + ViewFormDescriptionConverter.NEW_VALUE + ")");
        textfield.getBody().add(setNewValueOperation);
        return textfield;
    }

    private WidgetDescription createCheckboxWidget() {
        CheckboxDescription checkbox = FormFactory.eINSTANCE.createCheckboxDescription();
        checkbox.setName("CheckboxWidget");
        checkbox.setLabelExpression("aql:self.getDetailsViewLabel(eStructuralFeature)");
        checkbox.setValueExpression("aql:self.eGet(eStructuralFeature)");
        checkbox.setIsEnabledExpression("aql:not(eStructuralFeature.isReadOnly())");
        ChangeContext setNewValueOperation = ViewFactory.eINSTANCE.createChangeContext();
        setNewValueOperation.setExpression("aql:self.setNewValue(eStructuralFeature, " + ViewFormDescriptionConverter.NEW_VALUE + ")");
        checkbox.getBody().add(setNewValueOperation);
        return checkbox;
    }

    private WidgetDescription createRadioWidget() {
        RadioDescription radio = FormFactory.eINSTANCE.createRadioDescription();
        radio.setName("RadioWidget");
        radio.setLabelExpression("aql:self.getDetailsViewLabel(eStructuralFeature)");
        radio.setCandidatesExpression("aql:self.getEnumCandidates(eStructuralFeature)");
        radio.setCandidateLabelExpression("aql:candidate.name");
        radio.setValueExpression("aql:self.getEnumValue(eStructuralFeature)");
        radio.setIsEnabledExpression("aql:not(eStructuralFeature.isReadOnly())");
        ChangeContext setNewValueOperation = ViewFactory.eINSTANCE.createChangeContext();
        setNewValueOperation.setExpression("aql:self.setNewValue(eStructuralFeature, newValue.instance)");
        radio.getBody().add(setNewValueOperation);
        return radio;
    }

    private WidgetDescription createReferenceWidget() {
        ReferenceWidgetDescription refWidget = ReferenceFactory.eINSTANCE.createReferenceWidgetDescription();
        refWidget.setName("ReferenceWidget");
        refWidget.setLabelExpression("aql:self.getDetailsViewLabel(eStructuralFeature)");
        refWidget.setReferenceNameExpression("aql:eStructuralFeature");
        refWidget.setIsEnabledExpression("aql:not(eStructuralFeature.isReadOnly())");
        return refWidget;
    }
}
