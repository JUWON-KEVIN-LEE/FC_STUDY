package com.immymemine.kevin.subway.model.station_info;

/**
 * Created by quf93 on 2017-10-20.
 */

public class Json_station {
    private SearchSTNBySubwayLineService SearchSTNBySubwayLineService;

    public SearchSTNBySubwayLineService getSearchSTNBySubwayLineService ()
    {
        return SearchSTNBySubwayLineService;
    }

    public void setSearchSTNBySubwayLineService (SearchSTNBySubwayLineService SearchSTNBySubwayLineService)
    {
        this.SearchSTNBySubwayLineService = SearchSTNBySubwayLineService;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SearchSTNBySubwayLineService = "+SearchSTNBySubwayLineService+"]";
    }
}
