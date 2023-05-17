package com.mygdx.spaceaircraft.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.spaceaircraft.SpaceAircraftMain;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
//                return new GwtApplicationConfiguration(true);
                // Fixed size application:
                return new GwtApplicationConfiguration(SpaceAircraftMain.WIDTH, SpaceAircraftMain.HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SpaceAircraftMain();
        }
}