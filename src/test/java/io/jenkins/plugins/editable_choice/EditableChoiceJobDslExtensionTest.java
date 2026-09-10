package io.jenkins.plugins.editable_choice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import org.junit.Test;

public class EditableChoiceJobDslExtensionTest {

    @Test
    public void createsEditableChoiceParameterDefinition() {
        EditableChoiceParameterDefinition definition = (EditableChoiceParameterDefinition)
                new EditableChoiceJobDslExtension().editableChoiceParam(
                "FERMI_INSTANCE",
                Arrays.asList("hw9-nad-sa515m", "hw9-nad-sa515m_CM4_C1.18_5G"),
                "hw9-nad-sa515m",
                "Fermi instance branch");

        assertThat(definition.getName(), is("FERMI_INSTANCE"));
        assertThat(definition.getChoices(), contains(
                "hw9-nad-sa515m", "hw9-nad-sa515m_CM4_C1.18_5G"));
        assertThat(definition.getDefaultValue(), is("hw9-nad-sa515m"));
        assertThat(definition.getDescription(), is("Fermi instance branch"));
    }
}
