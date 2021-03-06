/**
 * Copyright © 2016-present Kriasoft.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

// Add extensions
module.exports.up = async db => {
  // these need to be run manually on local, or uncomment only for local
  if (process.env.CREATE_EXTENSIONS) {
    await db.raw('CREATE EXTENSION "uuid-ossp"');
    await db.raw('CREATE EXTENSION "hstore"');
  }
};

module.exports.down = async db => {};

module.exports.configuration = { transaction: true };
