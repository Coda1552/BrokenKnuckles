package codyhuh.brokenknuckles.util;

import software.bernie.geckolib.core.animation.RawAnimation;

public class BKAnimations {
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
}
