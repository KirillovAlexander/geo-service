package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    public void sentUSATest() {
        //given:
        Location locationUSA = new Location("Washington", Country.USA, null, 0);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.xx.xxx.xxx"))
                .thenReturn(locationUSA);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.xx.xxx.xxx");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        //when:
        String actual = messageSender.send(headers);

        //then:
        assertThat(actual, is(equalTo("Welcome")));
    }

    @Test
    public void sentRusTest() {
        //given:
        Location locationRussia = new Location("Moscow", Country.RUSSIA, null, 0);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.xx.xxx.xxx"))
                .thenReturn(locationRussia);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.xx.xxx.xxx");
        MessageSender messageSender = new MessageSenderImpl(geoService, new LocalizationServiceImpl());

        //when:
        String actual = messageSender.send(headers);

        //then:
        assertThat(actual, is(equalTo("Добро пожаловать")));
    }

}