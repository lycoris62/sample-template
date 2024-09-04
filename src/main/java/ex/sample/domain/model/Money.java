package ex.sample.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable // JPA 값객체 어노테이션
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 스펙상 엔티티나 임베디드 타입은 기본 생성자가 필수
public class Money {

    private Long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public Money add(Money money) {
        return new Money(this.amount + money.amount);
    }

    public Money subtract(final Money money) {
        return new Money(this.amount - money.amount);
    }

    public Money multiply(int multiplier) {
        return new Money(this.amount * multiplier);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
