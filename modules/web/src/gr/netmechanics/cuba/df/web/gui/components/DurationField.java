package gr.netmechanics.cuba.df.web.gui.components;

import java.time.Duration;

import com.haulmont.cuba.gui.components.Buffered;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.components.HasConversionErrorMessage;
import com.haulmont.cuba.gui.components.HasDatatype;
import com.haulmont.cuba.gui.components.HasInputPrompt;

/**
 * @author Panos Bariamis (pbaris)
 */
public interface DurationField extends Field<Duration>, HasInputPrompt,
    HasDatatype<Duration>, HasConversionErrorMessage, Buffered, Component.Focusable {

    String NAME = "durationField";

}
