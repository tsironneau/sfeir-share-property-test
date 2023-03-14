package io.github.tsironneau.share.prt.after;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.configurators.ArbitraryConfiguratorBase;
import net.jqwik.api.providers.TypeUsage;

public class ValidPrefixConfigurator extends ArbitraryConfiguratorBase {

    @Override
    protected boolean acceptTargetType(TypeUsage targetType) {
        return targetType.isAssignableFrom(Integer.class);
    }

    @SuppressWarnings("unused")
    public Arbitrary<Integer> configure(Arbitrary<Integer> arbitrary, ValidPrefix validPrefix) {

        return arbitrary.
                filter(number -> number > 0 && number <= IdGenerator.PREFIX_MAX_VALUE);
    }
}