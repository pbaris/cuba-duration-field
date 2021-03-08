package gr.netmechanics.cuba.df.web.gui.components;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.nullToEmpty;

import java.text.ParseException;
import java.time.Duration;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import com.haulmont.chile.core.datatypes.Datatype;
import com.haulmont.chile.core.datatypes.DatatypeRegistry;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.data.ConversionException;
import com.haulmont.cuba.web.gui.components.WebV8AbstractField;
import com.haulmont.cuba.web.widgets.CubaTextField;
import com.vaadin.shared.ui.ValueChangeMode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Panos Bariamis (pbaris)
 */
public class WebDurationField extends WebV8AbstractField<CubaTextField, String, Duration>
    implements DurationField, InitializingBean {

    protected Datatype<Duration> datatype;
    private String conversionErrorMessage;

    public WebDurationField() {
        component = new CubaTextField();
        attachValueChangeListener(component);
    }

    @Override
    public void afterPropertiesSet() {
        component.setValueChangeMode(ValueChangeMode.BLUR);
    }

    @Inject
    @SuppressWarnings("unchecked")
    public void setDatatypeRegistry(DatatypeRegistry datatypeRegistry) {
        this.datatype = datatypeRegistry.get("duration");
    }

    @Override
    public void focus() {
        component.focus();
    }

    @Override
    public int getTabIndex() {
        return component.getTabIndex();
    }

    @Override
    public void setTabIndex(int tabIndex) {
        component.setTabIndex(tabIndex);
    }

    @Override
    public void commit() {
        super.commit();
    }

    @Override
    @Nonnull
    public String getInputPrompt() {
        return component.getPlaceholder();
    }

    @Override
    public void setInputPrompt(@Nullable String inputPrompt) {
        component.setPlaceholder(inputPrompt);
    }

    @Override
    public void discard() {
        super.discard();
    }

    @Override
    public boolean isBuffered() {
        return super.isBuffered();
    }

    @Override
    public void setBuffered(boolean buffered) {
        super.setBuffered(buffered);
    }

    @Override
    public boolean isModified() {
        return super.isModified();
    }

    @Override
    protected String convertToPresentation(@Nullable Duration modelValue) throws ConversionException {
        return nullToEmpty(datatype.format(modelValue));
    }

    @Override
    protected Duration convertToModel(@Nullable String componentRawValue) throws ConversionException {
        String value = StringUtils.trimToNull(emptyToNull(componentRawValue));

        try {
            return datatype.parse(value);
        } catch (ParseException e) {
            throw new ConversionException(getConversionErrorMessageInternal(), e);
        }
    }

    @Override
    public void setConversionErrorMessage(String conversionErrorMessage) {
        this.conversionErrorMessage = conversionErrorMessage;
    }

    @Override
    public String getConversionErrorMessage() {
        return conversionErrorMessage;
    }

    @Override
    public void setDatatype(Datatype<Duration> datatype) {
        this.datatype = datatype;
    }

    @Override
    public Datatype<Duration> getDatatype() {
        return datatype;
    }

    protected String getConversionErrorMessageInternal() {
        String customErrorMessage = getConversionErrorMessage();
        if (StringUtils.isNotEmpty(customErrorMessage)) {
            return customErrorMessage;
        }

        if (datatype != null) {
            String msg = getDatatypeConversionErrorMsg(datatype);
            if (StringUtils.isNotEmpty(msg)) {
                return msg;
            }
        }

        return beanLocator.get(Messages.class).getMainMessage("databinding.conversion.error");
    }
}
