package gr.netmechanics.cuba.df.entity;

import static org.apache.commons.lang3.StringUtils.replaceOnce;
import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

import java.text.ParseException;
import java.time.Duration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.haulmont.chile.core.annotations.Ddl;
import com.haulmont.chile.core.annotations.JavaClass;
import com.haulmont.chile.core.datatypes.Datatype;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
@JavaClass(Duration.class)
@Ddl("bigint")
public class DurationDatatype implements Datatype<Duration> {

    @Override
    @Nonnull
    public String format(@Nullable Object value) {
        if (value == null) {
            return "";
        }

        String dhr = formatDuration(((Duration) value).toMillis(), "d'd' H'h' m'm'", false);
        dhr = replaceOnce(dhr, "0d", StringUtils.EMPTY);
        dhr = replaceOnce(dhr, " 0h", StringUtils.EMPTY);
        dhr = replaceOnce(dhr, " 0m", StringUtils.EMPTY);

        return dhr.trim();
    }

    @Override
    @Nonnull
    public String format(@Nullable Object value, @Nullable Locale locale) {
        return format(value);
    }

    @Nullable
    @Override
    public Duration parse(@Nullable String value) throws ParseException {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        Matcher m = Pattern.compile("\\s*(?:(\\d+)\\s*(?:days?|d))?" +
            "\\s*(?:(\\d+)\\s*(?:hours?|h))?" +
            "\\s*(?:(\\d+)\\s*(?:minutes?|m))?" +
            "\\s*", Pattern.CASE_INSENSITIVE)
            .matcher(value);

        if (!m.matches()) {
            throw new ParseException("Unparseable duration: \"" + value + "\"", 0);
        }

        int days = (m.start(1) == -1 ? 0 : Integer.parseInt(m.group(1)));
        int hours  = (m.start(2) == -1 ? 0 : Integer.parseInt(m.group(2)));
        int minutes  = (m.start(3) == -1 ? 0 : Integer.parseInt(m.group(3)));

        return Duration.ofMinutes((days * 24L + hours) * 60L + minutes);
    }

    @Nullable
    @Override
    public Duration parse(@Nullable String value, @Nullable Locale locale) throws ParseException {
        return parse(value);
    }
}
