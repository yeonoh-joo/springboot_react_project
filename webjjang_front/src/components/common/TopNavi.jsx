import { NavLink } from "react-router-dom"

function TopNavi(){
  return(
    <nav>
      <NavLink to={"/"}>Home</NavLink>&nbsp;
      <NavLink to={"/board/list"}>Board</NavLink>&nbsp;
    </nav>
  );
}

export default TopNavi;