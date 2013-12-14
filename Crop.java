package fi.paivola.foodmodel;

import fi.paivola.mapserver.core.DataFrame;
import fi.paivola.mapserver.core.setting.*;
import fi.paivola.mapserver.utils.Range;
import java.util.List;
import java.util.Map;
/**
 * @version 0.1
 * @author Jaakko Hannikainen
 * Generic class for field products, eg. maize.
 */

public abstract class Crop extends Edible {

    private String cropName; 
    private double waterMinimum;
    private double waterOptimal;
    private double waterMaximum;
    private double temperatureMinimum;
    private double temperatureOptimal;
    private double temperatureMaximum;
    private double sunlightMinimum;
    private double sunlightOptimal;
    private double sunlightMaximum;
    private double nutrientMinimum;
    private double nutrientOptimal;
    private double nutrientMaximum;
    
    public Crop(Map<String, Setting> settings) {
        super(settings);
        
        Range r = new Range(0, Integer.MAX_VALUE);
        
        this.settings.put("name", new SettingString("name", cropName));
        this.settings.put("minwater", new SettingDouble("water minimum"
                + "(cm/week)", getWaterMinimum(), r));
        this.settings.put("optwater", new SettingDouble("water optimum"
                + "(cm/week)", getWaterOptimal(), r));
        this.settings.put("maxwater", new SettingDouble("water maximum"
                + "(cm/week)", getWaterMaximum(), r));
        this.settings.put("mintemp", new SettingDouble("minimum average "
                + "temperature (C)", getTemperatureMinimum(), r));
        this.settings.put("opttemp", new SettingDouble("optimal average "
                + "temperature (C)", getTemperatureOptimal(), r));
        this.settings.put("maxtemp", new SettingDouble("maximum average "
                + "temperature (C)", getTemperatureMaximum(), r));
        this.settings.put("minsun", new SettingDouble("minimum sunlight "
                + "(hours / day)", getSunlightMinimum(), r));
        this.settings.put("optsun", new SettingDouble("optimal sunlight "
                + "(hours / day)", getSunlightOptimal(), r));
        this.settings.put("maxsun", new SettingDouble("maximum sunlight "
                + "(hours / day)", getSunlightMaximum(), r));
        this.settings.put("minnut", new SettingDouble("minimum nutrient "
                + "content", getNutrientMinimum(), r));
        this.settings.put("optnut", new SettingDouble("optimal nutrient "
                + "content", getNutrientOptimal(), r));
        this.settings.put("maxnut", new SettingDouble("maximum nutrient "
                + "content", getNutrientMaximum(), r));
    }

    public double onTick(DataFrame last) {
        return this.getWaterIndex(last) * this.getTemperatureIndex(last)
                * this.getSunshineIndex(last) * this.getNutrientIndex(last)
                * this.getArea() * this.getMaxYield();
    }
    
    private double getWaterIndex(DataFrame last) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private double getTemperatureIndex(DataFrame last) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private double getSunshineIndex(DataFrame last) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private double getNutrientIndex(DataFrame last) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private double getArea() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private double getMaxYield() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
     * @return the cropName
     */
    public final String getCropName() {
        return cropName;
    }

    /**
     * @param cropName the cropName to set
     */
    public final void setCropName(String cropName) {
        this.cropName = cropName;
    }
    
    /**
     * @return the waterMinimum
     */
    public final double getWaterMinimum() {
        return waterMinimum;
    }

    /**
     * @param waterMinimum the waterMinimum to set
     */
    public final void setWaterMinimum(double waterMinimum) {
        this.waterMinimum = waterMinimum;
    }

    /**
     * @return the waterOptimal
     */
    public final double getWaterOptimal() {
        return waterOptimal;
    }

    /**
     * @param waterOptimal the waterOptimal to set
     */
    public final void setWaterOptimal(double waterOptimal) {
        this.waterOptimal = waterOptimal;
    }

    /**
     * @return the waterMaximum
     */
    public final double getWaterMaximum() {
        return waterMaximum;
    }

    /**
     * @param waterMaximum the waterMaximum to set
     */
    public final void setWaterMaximum(double waterMaximum) {
        this.waterMaximum = waterMaximum;
    }

    /**
     * @return the temperatureMinimum
     */
    public final double getTemperatureMinimum() {
        return temperatureMinimum;
    }

    /**
     * @param temperatureMinimum the temperatureMinimum to set
     */
    public final void setTemperatureMinimum(double temperatureMinimum) {
        this.temperatureMinimum = temperatureMinimum;
    }

    /**
     * @return the temperatureOptimal
     */
    public final double getTemperatureOptimal() {
        return temperatureOptimal;
    }

    /**
     * @param temperatureOptimal the temperatureOptimal to set
     */
    public final void setTemperatureOptimal(double temperatureOptimal) {
        this.temperatureOptimal = temperatureOptimal;
    }

    /**
     * @return the temperatureMaximum
     */
    public final double getTemperatureMaximum() {
        return temperatureMaximum;
    }

    /**
     * @param temperatureMaximum the temperatureMaximum to set
     */
    public final void setTemperatureMaximum(double temperatureMaximum) {
        this.temperatureMaximum = temperatureMaximum;
    }

    /**
     * @return the sunlightMinimum
     */
    public final double getSunlightMinimum() {
        return sunlightMinimum;
    }

    /**
     * @param sunlightMinimum the sunlightMinimum to set
     */
    public final void setSunlightMinimum(double sunlightMinimum) {
        this.sunlightMinimum = sunlightMinimum;
    }

    /**
     * @return the sunlightOptimal
     */
    public final double getSunlightOptimal() {
        return sunlightOptimal;
    }

    /**
     * @param sunlightOptimal the sunlightOptimal to set
     */
    public final void setSunlightOptimal(double sunlightOptimal) {
        this.sunlightOptimal = sunlightOptimal;
    }

    /**
     * @return the sunlightMaximum
     */
    public final double getSunlightMaximum() {
        return sunlightMaximum;
    }

    /**
     * @param sunlightMaximum the sunlightMaximum to set
     */
    public final void setSunlightMaximum(double sunlightMaximum) {
        this.sunlightMaximum = sunlightMaximum;
    }

    /**
     * @return the nutrientMinimum
     */
    public final double getNutrientMinimum() {
        return nutrientMinimum;
    }

    /**
     * @param nutrientMinimum the nutrientMinimum to set
     */
    public final void setNutrientMinimum(double nutrientMinimum) {
        this.nutrientMinimum = nutrientMinimum;
    }

    /**
     * @return the nutrientOptimal
     */
    public final double getNutrientOptimal() {
        return nutrientOptimal;
    }

    /**
     * @param nutrientOptimal the nutrientOptimal to set
     */
    public final void setNutrientOptimal(double nutrientOptimal) {
        this.nutrientOptimal = nutrientOptimal;
    }

    /**
     * @return the nutrientMaximum
     */
    public final double getNutrientMaximum() {
        return nutrientMaximum;
    }

    /**
     * @param nutrientMaximum the nutrientMaximum to set
     */
    public final void setNutrientMaximum(double nutrientMaximum) {
        this.nutrientMaximum = nutrientMaximum;
    }

}
