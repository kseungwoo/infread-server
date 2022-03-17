package com.planters.parsley.constant;

import com.planters.parsley.exception.CommonException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NewsCategory {

    POLITICS, ECONOMY, SOCIETY, WORLD, CULTURE, SPORTS, LIFESTYLE;

    public String getSubUri(NewsBrand brand) {
        if (brand.equals(NewsBrand.JoongAng)) {
            if (this.equals(POLITICS)) {
                return "/politics";
            }
            else if (this.equals(ECONOMY)) {
                return "/money";
            }
            else if (this.equals(SOCIETY)) {
                return "/society";
            }
            else if (this.equals(WORLD)) {
                return "/world";
            }
            else if (this.equals(CULTURE)) {
                return "/culture";
            }
            else if (this.equals(SPORTS)) {
                return "/sports";
            }
            else if (this.equals(LIFESTYLE)) {
                return "/lifestyle";
            }
        }
        throw new CommonException(ResponseCode.INVALID_NEWS_CATEGORY);
    }
}
