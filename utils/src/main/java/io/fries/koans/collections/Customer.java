package io.fries.koans.collections;

import java.util.List;
import java.util.Objects;

public class Customer {

    public final String name;
    public final Integer age;
    public final Integer budget;
    public final List<Item> wantsToBuy;

    public Customer(final String name, final Integer age, final Integer budget, final List<Item> wantsToBuy) {
        this.name = name;
        this.age = age;
        this.budget = budget;
        this.wantsToBuy = wantsToBuy;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getBudget() {
        return budget;
    }

    public List<Item> getWantsToBuy() {
        return wantsToBuy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(budget, customer.budget) &&
                Objects.equals(wantsToBuy, customer.wantsToBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, budget, wantsToBuy);
    }
}
