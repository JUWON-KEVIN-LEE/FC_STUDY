package com.immymemine.kevin.bicycle.domain;

/**
 * Created by quf93 on 2017-10-18.
 */

public class Json {
    private GeoInfoBikeConvenientFacilitiesWGS GeoInfoBikeConvenientFacilitiesWGS;

    public GeoInfoBikeConvenientFacilitiesWGS getGeoInfoBikeConvenientFacilitiesWGS ()
    {
        return GeoInfoBikeConvenientFacilitiesWGS;
    }

    public void setGeoInfoBikeConvenientFacilitiesWGS (GeoInfoBikeConvenientFacilitiesWGS GeoInfoBikeConvenientFacilitiesWGS)
    {
        this.GeoInfoBikeConvenientFacilitiesWGS = GeoInfoBikeConvenientFacilitiesWGS;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [GeoInfoBikeConvenientFacilitiesWGS = "+GeoInfoBikeConvenientFacilitiesWGS+"]";
    }
}
