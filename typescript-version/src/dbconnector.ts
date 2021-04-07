import { Pool } from 'pg';

export default new Pool ({
  max: 20,
  connectionString: 'postgres://postgres@localhost:5432/test',
  idleTimeoutMillis: 30000
});
