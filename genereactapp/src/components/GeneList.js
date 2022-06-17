import React, { useState, useEffect } from "react";
import Masonry from "react-masonry-css";
import { Container, Badge, Col, Row, Spinner, Button } from "react-bootstrap";

const GeneList = () => {
  const [listItems, setListItems] = useState([]);
  const [isFetching, setIsFetching] = useState(false);
  const [page, setPage] = useState(0);
  const [symbol, setSymbol] = useState("");
  const [noGenesFound, setNoGenesFound] = useState(false);
  const [totalCount, setTotalCount] = useState(0);
  const breakpointColumnsObj = {
    default: 2,
    1100: 1,
    700: 1,
    500: 1,
  };

  useEffect(() => {
    fetchData();
    window.addEventListener("scroll", handleScroll);
  }, []);

  const handleScroll = () => {
    const scrollHeight = Math.ceil(
      window.innerHeight + document.documentElement.scrollTop
    );
    if (scrollHeight !== document.documentElement.offsetHeight || isFetching) {
      return;
    }
    setIsFetching(true);
  };

  const fetchData = async () => {
    setTimeout(async () => {
      if (symbol) {
        const result = await fetch(
          `http://localhost:9090/geneservice/bysymbol?symbol=${symbol}&offset=${page}&pageSize=13`
        );
        const data = await result.json();
        setPage(page + 1);
        if (data.response) {
          setListItems(() => {
            return [...listItems, ...data.response];
          });
          debugger;
          setTotalCount(data.totalCount);
          setNoGenesFound(false);
        } else {
          setTotalCount(0);
          setNoGenesFound(true);
        }
      }
      setIsFetching(false);
    }, 800);
  };

  useEffect(() => {
    if (!isFetching) return;
    fetchMoreListItems();
  }, [isFetching]);

  const fetchMoreListItems = () => {
    fetchData();
  };

  return (
    <>
      <Container>
        <input
          type="text"
          className="form-control"
          placeholder="Enter a symbol"
          onChange={(e) => setSymbol(e.target.value)}
        />
        <Button
          className="mt-3 mt-4 w-100"
          variant="success"
          onClick={() => {
            setIsFetching(true);
            setListItems([]);
            setPage(0);
            fetchData();
          }}
        >
          Search
        </Button>
        <br />
        <br />
        <hr />
        <br />
        {noGenesFound && !isFetching ? (
          <p>
            <b>No results, please try different symbol</b>
          </p>
        ) : (
          <>
            {totalCount > 0 ? (
              <h5 className="mb-4">
                <b>{totalCount}</b> genes found with the search term{" "}
                <b>'{symbol}'</b>
              </h5>
            ) : null}
            <Masonry
              breakpointCols={breakpointColumnsObj}
              className="my-masonry-grid"
              columnClassName="my-masonry-grid_column"
            >
              {listItems.map((listItem, index) => (
                <div className="card" key={listItem.geneId}>
                  <div className="container">
                    <h3>
                      <b>ID:</b> {listItem.geneId}
                    </h3>
                    <hr />
                    <Row>
                      <Col xs={8}>
                        <p>
                          <b>Gene type: </b>
                          <Badge bg="success">{listItem.geneType.name}</Badge>
                        </p>
                        <p>
                          <b>Symbol: </b>
                          {listItem.symbol}
                        </p>
                        <p>
                          <b>TaxId: </b>
                          {listItem.taxId}
                        </p>
                        <p>
                          <b>Locus tag: </b>
                          {listItem.locusTag}
                        </p>
                        <p>
                          <b>Description: </b>
                          {listItem.description}
                        </p>
                        <p>
                          <b>Other designations: </b>
                          {listItem.otherDesignations
                            ? listItem.otherDesignations
                            : "none"}
                        </p>
                      </Col>
                      <Col xs={4}>
                        <img
                          alt="gene"
                          height={85}
                          src={`/gene_images/gene${(index % 7) + 1}.jpg`}
                        />
                      </Col>
                    </Row>
                  </div>
                </div>
              ))}
            </Masonry>
          </>
        )}
      </Container>
      {isFetching ? (
        <div className="w-100 text-center">
          <Spinner
            animation="border"
            style={{ width: "5rem", height: "5rem" }}
            className="mb-4 Loader"
          >
            <span className="visually-hidden">Loading...</span>
          </Spinner>
          <br />
          <br />
        </div>
      ) : null}
    </>
  );
};

export default GeneList;
