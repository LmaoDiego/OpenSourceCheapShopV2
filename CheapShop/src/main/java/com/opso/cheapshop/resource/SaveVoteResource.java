//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.opso.cheapshop.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveVoteResource {
    @NotNull
    @Size(
            max = 100
    )
    private String name;

    public SaveVoteResource() {
    }

    public String getName() {
        return this.name;
    }

    public SaveVoteResource setName(String name) {
        this.name = name;
        return this;
    }
}
