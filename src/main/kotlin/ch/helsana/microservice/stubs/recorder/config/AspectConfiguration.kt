package ch.helsana.microservice.stubs.recorder.config

import org.aspectj.lang.annotation.Pointcut
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@Configuration
class AspectConfiguration {

    //    @Pointcut("within(ch.helsana.microservice.stubs.recorder.client.ApiBridgeClient)")
    @Pointcut("within(org.apache.wss4j.dom.common.SAML2CallbackHandler)")
    fun monitor() {
    }

    @Bean
    fun performanceMonitorInterceptor() = PerformanceMonitorInterceptor(true)


    @Bean
    fun performanceMonitorAdvisor(): Advisor = AspectJExpressionPointcut().apply {
        expression = "within(ch.helsana.microservice.stubs.recorder.client.ApiBridgeClient)"
    }.run {
        DefaultPointcutAdvisor(this, performanceMonitorInterceptor())
    }

}