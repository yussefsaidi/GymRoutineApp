package com.yussefsaidi.gymroutine.di;

import androidx.lifecycle.ViewModelProvider;

import com.yussefsaidi.gymroutine.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule  {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

}
