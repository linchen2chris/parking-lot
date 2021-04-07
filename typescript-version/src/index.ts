import express from 'express';
import pool from './dbconnector';
const port = parseInt(process.env.PORT || '4000');

const app = express();

const initDB = async () => {
	const client = await pool.connect();
	const sql = "SELECT * FROM student";
	const { rows } = await client.query(sql);
	console.log('Connected', rows);
}

initDB();
