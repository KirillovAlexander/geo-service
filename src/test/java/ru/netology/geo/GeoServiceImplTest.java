package ru.netology.geo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    private static GeoService geoService;

    @BeforeAll
    public static void init() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void byIpLocalhostTest() {
        Location actual = geoService.byIp(GeoServiceImpl.LOCALHOST);
        assertThat(actual.getCountry(), is(equalTo(null)));
    }

    @Test
    public void byIpMoscowTest() {
        Location actual = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        assertThat(actual.getCountry(), is(equalTo(Country.RUSSIA)));
    }

    @Test
    public void byIpNYTest() {
        Location actual = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        assertThat(actual.getCountry(), is(equalTo(Country.USA)));
    }

    @Test
    public void byIpNullAsStringTest() {
        Location actual = geoService.byIp("null");
        assertNull(actual);
    }

    @Test
    public void byIpNullTest() {
        assertThrows(NullPointerException.class, () -> geoService.byIp(null));
    }
}