package com.fyusion.sdk.common.ext.filter;

import com.fyusion.sdk.common.ext.filter.a.p;

/* compiled from: Unknown */
public class GingerToneCurve extends ToneCurveFilter {
    public GingerToneCurve() {
        super(ImageFilterAbstractFactory.GINGER);
    }

    public Class getImplementationClass() {
        return p.class;
    }
}
