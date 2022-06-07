
import { Link } from 'react-router-dom';

const Main = (props) => {
	return (
		<>
			<h3>10시부터 티켓팅이 가능합니다!</h3>
				<Link to="/ticketing"><button>티켓팅하기</button></Link>
		</>
	);
};

export default Main;