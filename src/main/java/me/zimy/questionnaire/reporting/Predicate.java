package me.zimy.questionnaire.reporting;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 1/17/15.
 */
public interface Predicate<T> {
    public boolean evaluate(T object);
}
