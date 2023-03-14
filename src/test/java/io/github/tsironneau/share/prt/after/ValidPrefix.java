package io.github.tsironneau.share.prt.after;

import java.lang.annotation.*;

/**
 * Constrain the range of a generated number to be greater than 0 and less than {@link IdGenerator#PREFIX_MAX_VALUE}.
 * <p>
 * Applies to numeric parameters which are also annotated with {@code @ForAll}.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPrefix {
}