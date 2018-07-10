package com.app.horizon.core.dagger.components;

import com.app.horizon.core.dagger.modules.database.HorizonDatabaseModule;
import com.app.horizon.core.dagger.modules.external.ContextModule;
import com.app.horizon.core.dagger.scopes.MainAppScope;

import dagger.Component;

/**
 * Created by Ayokunle Paul on 7/3/18.
 */
@MainAppScope
@Component(modules = {HorizonDatabaseModule.class, ContextModule.class})
public interface HorizonMainComponent {
}
