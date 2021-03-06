package com.moon.util.console;

import com.moon.enums.Const;
import com.moon.lang.IntUtil;
import com.moon.util.Console.Level;
import com.moon.util.console.ConsoleControl.Classify;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * @author benshaoye
 */
public final class ConsoleSettings {

    public static final ConsoleSettings DEFAULT;

    static {
        DEFAULT = new ConsoleSettings(
            false, true, Level.PRINT, Const.EMPTY,
            new Classify[]{}, Const.EMPTY);
    }

    private final boolean html;
    private final boolean async;

    private final String filenameFormat;
    private final String pattern;

    private final Classify[] classifies;
    private final Level lowestLevel;

    public ConsoleSettings(ConsoleControl control) {
        this(control.html(), control.async(), control.lowestLevel(), control.pattern(),
            control.classifies(), control.filenameFormat());
    }

    public ConsoleSettings(
        boolean html, boolean async, Level lowestLevel, String pattern,
        Classify[] classifies, String filenameFormat) {
        IntUtil.requireLt(classifies.length, 10);

        this.html = html;
        this.async = async;

        this.filenameFormat = requireNonNull(filenameFormat).trim();
        this.pattern = requireNonNull(pattern).trim();

        this.lowestLevel = requireNonNull(lowestLevel);
        this.classifies = requireNonNull(classifies);
    }

    public boolean isHtml() {
        return html;
    }

    public boolean isAsync() {
        return async;
    }

    public Classify[] getClassifies() {
        return classifies;
    }

    public String getFilenameFormat() {
        return filenameFormat;
    }

    public Level getLowestLevel() {
        return lowestLevel;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Boolean.hashCode(html);
        result = 31 * result + Boolean.hashCode(async);
        result = 31 * result + lowestLevel.hashCode();
        result = 31 * result + filenameFormat.hashCode();
        result = 31 * result + pattern.hashCode();
        result = 31 * result + Arrays.hashCode(classifies);
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ConsoleSettings settings = (ConsoleSettings) object;
        return html == settings.html &&
            async == settings.async &&
            lowestLevel == settings.lowestLevel &&
            filenameFormat.equals(settings.filenameFormat) &&
            pattern.equals(settings.pattern) &&
            Arrays.equals(classifies, settings.classifies);
    }
}
