# 一次因为Jupiter而引发的思考

Jupiter和Junit5之间有什么联系？

Jupiter提供了哪些新的测试方法？

如何用IDEA和Jupiter生成可读性更好的测试报告？

### 让代码正常运行

### 配置项目
 * 打开pom.xml，Open as project
 * 按如下方式将src.main.java的java设置为Sources, 将src.test.java的java设置为Tests
 ![设置Sources和Tests](https://github.com/yanggfann/JunitProbe/blob/main/Junit4Junit5Jupiter/image/Sources%20Tests.png)
 * 在IntelliJ的Java8项目中，尽管已经将Project SDK和Project Launguage Level设置为Java 8，
 在运行单元测试的过程中，如果报java:javaTask:source release 8 requires target release 8,
 请按如下两种方式解决
    * 方案1
    
    在IntelliJ的【Preferences】->【Compiler】->【Java Compiler】中将Target bytecode verion改为1.8，
    如下图所示
    ![target release 1.8](https://github.com/yanggfann/JunitProbe/blob/main/Junit4Junit5Jupiter/image/target%20release%201.8.png)
    
    * 方案2
    
    修改.idea/compiler.xml
    ```
        <bytecodeTargetLevel>
          <module name="Junit4-Junit5" target="1.8" />
        </bytecodeTargetLevel>
    ```
 

## Junit5

目前Java领域内最为流行的单元测试框架  ------    JUnit

Junit的最新版本JUnit5于2017年发布。

Junit 5 = Junit Platform + Junit Jupiter + Junit Vintage

Junit Platform: Junit Platform是在JVM上启动测试框架的基础，不仅支持Junit自制的测试引擎，其他测试引擎也都可以接入。

Junit Jupiter: Junit Jupiter提供了JUnit5的新的编程模型，是JUnit5新特性的核心。内部 包含了一个测试引擎，用于在Junit Platform上运行。

Junit Vintage: 由于JUnit已经发展多年，为了照顾老的项目，JUnit Vintage提供了兼容JUnit4.x,Junit3.x的测试引擎。


## Dependency

### Junit4

```
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
```

当前dependency会引入junit:4.12和hamcrest-core:1.3的包

### Junit vintage engine

```
    <dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <version>5.6.2</version>
        <scope>test</scope>
    </dependency>
```

当前dependency会引入unit:4.13, apiguardian-api:1.1.0, hamcrest-core:1.3, junit-platform-commons:1.6.2, 
junit-platform-engine:1.6.2, junit-vintage-engine:5.6.2, opentest4j:1.2.0的包

### Jupiter

```
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.6.2</version>
        <scope>test</scope>
    </dependency>
```

当前dependency会引入apiguardian-api:1.1.0, junit-Jupiter-api:5.6.2, junit-platform-commons:1.6.2, opentest4j:1.2.0的包

### Junit4和Junit5的注解区别

|Junit5|Junit4|说明|
|------|------|---|
|@Test|@Test|被注解的方法是一个测试方法。与 JUnit 4 相同。|
|@BeforeAll|@BeforeClass|被注解的（静态）方法将在当前类中的所有 @Test 方法前执行一次。|
|@BeforeEach|@Before|被注解的方法将在当前类中的每个 @Test 方法前执行。|
|@AfterEach|@After|被注解的方法将在当前类中的每个 @Test 方法后执行。|
|@AfterAll|@AfterClass|被注解的（静态）方法将在当前类中的所有 @Test 方法后执行一次。|
|@Disabled|@Ignore|被注解的方法不会执行（将被跳过），但会报告为已执行|

Junit4中的@Test是import org.junit.Test;
Jupiter中的@Test是import org.junit.jupiter.api.Test;

### 断言

在Junit4和Junit5中均有标准断言

|断言方法|说明|
|-------|---|
|assertEquals(expected, actual)|如果 expected 不等于 actual ，则断言失败。|
|assertFalse(booleanExpression)|如果 booleanExpression 不是 false ，则断言失败。|
|assertNull(actual)|如果 actual 不是 null ，则断言失败。|
|assertNotNull(actual)|如果 actual 是 null ，则断言失败。|
|assertTrue(booleanExpression)|如果 booleanExpression 不是 true ，则断言失败。|

Junit4中任何断言失败，测试就会在该位置失败，意味着不会执行任何其他断言。例如StudentTest中的should_test_every_test。
```
    @Test
    public void should_test_every_test() {
        //given when
        int expected = 6;
        int actual = 10 - 4;
        Object nullValue = null;

        //then
        assertEquals(expected, actual);
        assertFalse(true);
        assertNull(nullValue);
        assertTrue(false);
    }
```

如果希望所有 断言都会执行，即使一个或多个断言失败也是如此，该怎么做呢？

可以使用Jupiter中提供的aseertAll方法
```
    @Test
    @DisplayName("test assertAll")
    void should_test_every_test() {
        //given when
        int expected = 4;
        int actual = 2 + 2;
        Object nullValue = null;

        //then
        assertAll(
                "Assert All of these",
                () -> assertEquals(expected, actual),
                () -> assertFalse(nullValue == null),
                () -> assertNull(nullValue),
                () -> assertNotNull("Hello Word!"),
                () -> assertTrue(nullValue != null));
    }
```

### @DisplayName

可以在类和方法中添加@DisplayName注释。这个名称在生成报告时使用，这使得描述测试的目的和追踪失败更容易

运行单元测试后，点击如下位置则可生成html报告
![generate report](https://github.com/yanggfann/JunitProbe/blob/main/Junit4Junit5Jupiter/image/generate%20report.png)

Student生成的单元测试报告为Test Results - StudentTest.html

StudentJupiterTest生成的单元测试报告为Test Results - StudentJupiterTest.html