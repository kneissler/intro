package com.kneissler.language.util;


import com.kneissler.language.executable.ExecutableMethod;
import com.kneissler.language.executable.ExecutableModule;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

public class JsonTest {

    @Test
    public void testJson() {
        ExecutableMethod m = new ExecutableMethod();
        m.parameters = new String[]{"par1", "par2"};
        ExecutableModule test = new ExecutableModule("test", new ExecutableMethod[]{m});
        String expected = "{\"name\"=\"test\",\"methods\"=[{\"parameters\"=[\"par1\",\"par2\"]}]}";
        Assert.assertEquals(expected, Json.asString(test));
    }
}