package io.jenkins.plugins.editable_choice;

import hudson.Extension;
import java.util.List;
import javaposse.jobdsl.dsl.helpers.BuildParametersContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import javaposse.jobdsl.plugin.DslExtensionMethod;

/** Adds native Job DSL support for editable choice parameters. */
@Extension(optional = true)
public class EditableChoiceJobDslExtension extends ContextExtensionPoint {

    /**
     * Defines an editable choice parameter using the first choice as default.
     *
     * @param parameterName parameter name
     * @param choices predefined suggestions
     * @return parameter definition XML
     */
    @DslExtensionMethod(context = BuildParametersContext.class)
    public Object editableChoiceParam(final String parameterName, final List<String> choices) {
        return editableChoiceParam(parameterName, choices, null, null);
    }

    /**
     * Defines an editable choice parameter with a default value.
     *
     * @param parameterName parameter name
     * @param choices predefined suggestions
     * @param defaultValue initial value
     * @return parameter definition XML
     */
    @DslExtensionMethod(context = BuildParametersContext.class)
    public Object editableChoiceParam(
            final String parameterName, final List<String> choices, final String defaultValue) {
        return editableChoiceParam(parameterName, choices, defaultValue, null);
    }

    /**
     * Defines an editable choice parameter with predefined suggestions.
     *
     * @param parameterName parameter name
     * @param choices predefined suggestions
     * @param defaultValue initial value
     * @param description parameter description
     * @return parameter definition XML
     */
    @DslExtensionMethod(context = BuildParametersContext.class)
    public Object editableChoiceParam(
            final String parameterName,
            final List<String> choices,
            final String defaultValue,
            final String description) {
        EditableChoiceParameterDefinition definition =
                new EditableChoiceParameterDefinition(parameterName);
        definition.setChoices(choices);
        if (defaultValue != null) {
            definition.setDefaultValue(defaultValue);
        }
        if (description != null) {
            definition.setDescription(description);
        }
        return definition;
    }
}
