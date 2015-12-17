package com.tuannt.excollect.opengl.objects;

import com.tuannt.excollect.opengl.Geometry;


/**
 * Comment
 *
 * @author TuanNT
 */
public class ParticleShooter {
    private final Geometry.Point position;
    private final Geometry.Vector direction;
    private final int color;

    public ParticleShooter(Geometry.Point position, Geometry.Vector direction, int color) {
        this.position = position;
        this.direction = direction;
        this.color = color;
    }

    public void addParticles(ParticleSystem particleSystem, float currentTime, int count) {
        for (int i = 0; i < count; i++) {
            particleSystem.addParticle(position, color, direction, currentTime);
        }
    }
}
