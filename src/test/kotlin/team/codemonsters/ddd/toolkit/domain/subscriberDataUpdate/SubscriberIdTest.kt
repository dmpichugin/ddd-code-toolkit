package team.codemonsters.ddd.toolkit.domain.subscriberDataUpdate

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SubscriberIdTest {
    @Test
    fun success() {
        val sut = SubscriberId.emerge("888")
        assertThat(sut.isSuccess).isTrue
        assertThat(sut.getOrThrow().value).isEqualTo("888")
    }

    @Test
    fun successWith6Digits() {
        val sut = SubscriberId.emerge("123456")
        assertThat(sut.isSuccess).isTrue
        assertThat(sut.getOrThrow().value).isEqualTo("123456")
    }

    @Test
    fun failWithMoreThen6Digits() {
        val sut = SubscriberId.emerge("1234567")
        assertThat(sut.isFailure).isTrue
        assertThat(sut.exceptionOrNull()!!.message).isEqualTo("Subscriber Id consists of numbers maximum length 6")
    }

    @Test
    fun failWithWrongFormat() {
        val sut = SubscriberId.emerge("L124S")
        assertThat(sut.isFailure).isTrue
        assertThat(sut.exceptionOrNull()!!.message).isEqualTo("Subscriber Id consists of numbers maximum length 6")
    }

    @Test
    fun failWithWrongFormat2() {
        val sut = SubscriberId.emerge("11aa")
        assertThat(sut.isFailure).isTrue
        assertThat(sut.exceptionOrNull()!!.message).isEqualTo("Subscriber Id consists of numbers maximum length 6")
    }

    @Test
    fun failWithEmpty() {
        val sut = SubscriberId.emerge("")
        assertThat(sut.isFailure).isTrue
        assertThat(sut.exceptionOrNull()!!.message).isEqualTo("Subscriber Id consists of numbers maximum length 6")
    }

}