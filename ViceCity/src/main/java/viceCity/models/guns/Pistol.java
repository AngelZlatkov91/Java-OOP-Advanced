package viceCity.models.guns;

public class Pistol extends BaseGun{
    private static final int INITIAL_BULLET_PER_BARREL = 10;
    private static final int INITIAL_TOTAL_BULLETS = 100;



    public Pistol(String name) {
        super(name, INITIAL_BULLET_PER_BARREL, INITIAL_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (canFire()) {
          setBulletsPerBarrel(getBulletsPerBarrel() - 1);
          if (getBulletsPerBarrel() == 0) {
              if (getTotalBullets() > INITIAL_BULLET_PER_BARREL) {
                  setTotalBullets(getTotalBullets() - 10);
                  setBulletsPerBarrel(INITIAL_BULLET_PER_BARREL);
              } else {
                  setBulletsPerBarrel(getTotalBullets());
                  setTotalBullets(0);
              }
          }
        } else {
            return 0;
        }
        return 1;
    }
}
