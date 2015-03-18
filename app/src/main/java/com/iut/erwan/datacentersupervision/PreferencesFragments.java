package com.iut.erwan.datacentersupervision;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PreferencesFragments extends PreferenceFragment {

    // Ctes string, clés de sauvegarde des préférences
    public static final String PREFKEY_IPSERVEUR ="PREFKEY_IPSERVEUR";
    public static final String PREFKEY_PORTSERVEUR="PREFKEY_PORTSERVEUR";
    public static final String PREFKEY_USERNAME ="PREFKEY_USERNAME";
    public static final String PREFKEY_PASSWORD ="PREFKEY_PASSWORD";

    public static final String PREFKEY_IPAGENTV1="PREFKEY_IPAGENTV1";
    public static final String PREFKEY_PORTAGENTV1 ="PREFKEY_PORTAGENTV1";
    public static final String PREFKEY_COMMUNAUTEDISC ="PREFKEY_COMMUNAUTEDISC";

    public static final String PREFKEY_IPAGENTV1_S="PREFKEY_IPAGENTV1_S";
    public static final String PREFKEY_PORTAGENTV1_S ="PREFKEY_PORTAGENTV1_S";
    public static final String PREFKEY_COMMUNAUTESONDE ="PREFKEY_COMMUNAUTESONDE";

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        addPreferencesFromResource(R.xml.userpreferences);
    }
}
