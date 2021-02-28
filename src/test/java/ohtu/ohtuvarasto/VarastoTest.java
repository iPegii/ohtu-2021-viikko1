package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLis��Tilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void laitetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(11);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yritet��nOttaaLiikaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(9);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void teeDoubleVarastoNegatiivisellaAlkusaldolla() {
        Varasto test = new Varasto(2,-2);

        assertEquals(0, test.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void teeDoubleVarastoYhdenAlkusaldolla() {
        Varasto test = new Varasto(2,1);

        assertEquals(1, test.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void teeDoubleVarastoNegatiivisellaTilavuudella() {
        Varasto test = new Varasto(-1,1);

        assertEquals(0, test.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void teeNormiVarastoNegatiivisellaTilavuudella() {
        Varasto test = new Varasto(-1);

        assertEquals(0, test.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonNegatiivinne() {
        Varasto test = new Varasto(2);
        
        test.lisaaVarastoon(-1);

        assertEquals(0, test.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaNegatiivinen() {
        Varasto test = new Varasto(2);
        
        test.lisaaVarastoon(1);
        
        test.otaVarastosta(-1);

        assertEquals(1, test.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringTesti() {
        Varasto test = new Varasto(3);
        
        test.lisaaVarastoon(2);

        assertEquals(test.toString(),"saldo = 2.0, viel� tilaa 1.0");
    }


}