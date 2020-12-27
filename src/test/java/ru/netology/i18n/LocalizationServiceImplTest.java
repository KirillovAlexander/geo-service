package ru.netology.i18n;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    private static LocalizationService localizationService;

    @BeforeAll
public static void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void localeRusTest() {
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void localeEngTest() {
        assertEquals("Welcome", localizationService.locale(Country.USA));
        assertEquals("Welcome", localizationService.locale(Country.GERMANY));
        assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
    }
}