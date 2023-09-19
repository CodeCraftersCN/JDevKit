package cn.org.codecrafters.simplejwt.autoconfiguration.conditions;

import cn.org.codecrafters.guid.GuidCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * The conditions to create bean {@code jtiCreator}.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
@Slf4j
public class GuidCreatorCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final var beanFactory = Objects.requireNonNull(context.getBeanFactory());
        var isContainJtiCreator = beanFactory.containsBean("jtiCreator");
        if (isContainJtiCreator) {
            return !(beanFactory.getBean("jtiCreator") instanceof GuidCreator<?>);
        }
        return true;
    }
}
