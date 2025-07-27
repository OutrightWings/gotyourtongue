package com.outrightwings.gotyourtongue;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.BooleanValue DEFAULT_SCARE = BUILDER
            .comment("Are elder guardians scary when they spawn?")
            .define("default_scare",true);
    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean getDefaultScare(){
        return DEFAULT_SCARE.get();
    }
}

