package edu.unisabana.dyas.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void validateRegistryResult() {
        Person person = new Person("Juan", 123, 25, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    @Test
    public void shouldReturnUnderageWhenPersonIsYoungerThan18() {
        Person underage = new Person("Laura", 456, 16, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(underage);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldReturnDeadWhenPersonIsNotAlive() {
        Person deceased = new Person("Carlos", 789, 30, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(deceased);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldReturnInvalidAgeWhenPersonHasNegativeAge() {
        Person invalidAge = new Person("Ana", 321, -5, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(invalidAge);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldReturnInvalidAgeWhenPersonHasAgeOver120() {
        Person oldAge = new Person("Roberto", 654, 130, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(oldAge);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldReturnDuplicatedWhenPersonRegistersTwice() {
        Person original = new Person("María", 987, 28, Gender.FEMALE, true);
        registry.registerVoter(original); // Primera vez
        RegisterResult result = registry.registerVoter(original); // Segunda vez
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }

}
