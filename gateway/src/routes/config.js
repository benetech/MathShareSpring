import db, { insertOrUpdate } from '../db';

import { Router } from 'express';

export const defaultRedirect = process.env.CORS_ORIGIN.split(',')[0];

const router = new Router();

router.get('/config', async (req, res) => {
  if (!req.user) {
    res.status(401).send({ message: 'Unauthorized' });
  }
  try {
    const config = (await db
      .table('users')
      .innerJoin('user_configs', 'user_configs.user_id', 'users.id')
      .where({
        'users.id': req.user.id,
      })
      .select('user_configs.ui', 'user_configs.analytics', 'user_configs.tts')
      .first()) || {
      ui: {},
      analytics: {},
      tts: {},
    };
    res.status(200).send(config);
  } catch (error) {
    console.log('error', error);
    res.status(500).end();
  }
});

router.post('/config', async (req, res) => {
  if (!req.user) {
    res.status(401).send({ message: 'Unauthorized' });
  }
  try {
    const body = req.body || {};
    const allowedKeys = {
      ui: {
        alertAutoClose: 'number',
        font: 'string',
        lineHeight: 'number',
        letterSpacing: 'number',
      },
      tts: {
        speed: 'number',
      },
    };
    Object.keys(body).forEach(key => {
      if (allowedKeys[key]) {
        body[key] = JSON.stringify(
          Object.keys(body[key]).reduce((collected, current) => {
            if (allowedKeys[key][current]) {
              if (typeof body[key][current] === allowedKeys[key][current]) {
                collected[current] = body[key][current];
              } else {
                collected[current] = '';
              }
            }
            return collected;
          }, {}),
        );
      } else {
        delete body[key];
      }
    });
    body.user_id = req.user.id;
    await insertOrUpdate('user_configs', body, 'user_id');
    const config = await db
      .table('users')
      .innerJoin('user_configs', 'user_configs.user_id', 'users.id')
      .where({
        'users.id': req.user.id,
      })
      .select('user_configs.ui', 'user_configs.analytics', 'user_configs.tts')
      .first();
    res.status(200).send(config);
  } catch (error) {
    console.log('error', error);
    res.status(500).end();
  }
});

export default router;
